package com.agah.furkan.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.cart.AddProductToCartUseCase
import com.agah.furkan.domain.cart.RemoveProductFromCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CartScreenViewModel @Inject constructor(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase
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
            val result = removeProductFromCartUseCase(productId = productId)
            val state = when (result) {
                is DomainResult.Success -> {
                    RemoveProductFromCartUiState.Success
                }
                is DomainResult.Failure -> {
                    RemoveProductFromCartUiState.Error(result.error.message)
                }
            }
            _removeProductState.trySend(state)
        }
    }

    fun addProductToCart(productId: Long) {
        _addProductToCartState.trySend(AddProductToCartUiState.Loading)

        viewModelScope.launch {
            val result = addProductToCartUseCase(productId = productId)
            val state = when (result) {
                is DomainResult.Success -> {
                    AddProductToCartUiState.Success
                }

                is DomainResult.Failure -> {
                    AddProductToCartUiState.Error(result.error.message)
                }
            }
            _addProductToCartState.trySend(state)
        }
    }
}
