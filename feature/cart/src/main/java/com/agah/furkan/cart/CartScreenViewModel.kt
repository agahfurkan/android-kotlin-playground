package com.agah.furkan.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.cart.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.preferences.UserPreference
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
}