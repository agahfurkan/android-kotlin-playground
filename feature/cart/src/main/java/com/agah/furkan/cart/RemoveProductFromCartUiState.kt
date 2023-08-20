package com.agah.furkan.cart

sealed class RemoveProductFromCartUiState {
    object Success : RemoveProductFromCartUiState()
    object Loading : RemoveProductFromCartUiState()
    data class Error(val message: String) : RemoveProductFromCartUiState()
}