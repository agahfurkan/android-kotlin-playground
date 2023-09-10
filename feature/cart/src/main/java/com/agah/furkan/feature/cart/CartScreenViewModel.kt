package com.agah.furkan.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.cart.CartRepository
import com.agah.furkan.data.cart.remote.model.request.AddProductToCartBody
import com.agah.furkan.data.cart.remote.model.request.RemoveProductFromCartBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CartScreenViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    private val _removeProductState =
        Channel<RemoveProductFromCartUiState>(capacity = Channel.BUFFERED)
    val removeProductState = _removeProductState.receiveAsFlow()

    private val _addProductToCartState =
        Channel<AddProductToCartUiState>(capacity = Channel.BUFFERED)
    val addProductToCartState = _addProductToCartState.receiveAsFlow()

    fun removeProductFromCart(productId: Long) {
        _removeProductState.trySend(RemoveProductFromCartUiState.Loading)

        viewModelScope.launch {
            val result = cartRepository.removeProductFromCart(
                RemoveProductFromCartBody(
                    userId = userPreference.getUserId(),
                    productId = productId
                )
            )
            val state = when (result) {
                is Result.Success -> {
                    RemoveProductFromCartUiState.Success
                }

                is Result.Failure -> {
                    RemoveProductFromCartUiState.Error(result.error.errorMessage)
                }
            }
            _removeProductState.trySend(state)
        }
    }

    fun addProductToCart(productId: Long) {
        _addProductToCartState.trySend(AddProductToCartUiState.Loading)

        viewModelScope.launch {
            val result = cartRepository.addProductToCart(
                AddProductToCartBody(
                    userId = userPreference.getUserId(),
                    productId = productId
                )
            )
            val state = when (result) {
                is Result.Success -> {
                    AddProductToCartUiState.Success
                }

                is Result.Failure -> {
                    AddProductToCartUiState.Error(result.error.errorMessage)
                }
            }
            _addProductToCartState.trySend(state)
        }
    }
}