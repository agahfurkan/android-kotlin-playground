package com.agah.furkan.feature.cart

sealed class AddProductToCartUiState {
    object Success : AddProductToCartUiState()
    object Loading : AddProductToCartUiState()
    data class Error(val message: String) : AddProductToCartUiState()
}