package com.agah.furkan.feature.cart

sealed class RemoveProductFromCartUiState {
    object Success : RemoveProductFromCartUiState()
    object Loading : RemoveProductFromCartUiState()
    data class Error(val message: String) : RemoveProductFromCartUiState()
}