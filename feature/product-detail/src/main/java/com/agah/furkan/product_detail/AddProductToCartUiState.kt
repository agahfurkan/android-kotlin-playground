package com.agah.furkan.product_detail

sealed class AddProductToCartUiState {
    object Success : AddProductToCartUiState()
    object Loading : AddProductToCartUiState()
    data class Error(val message: String) : AddProductToCartUiState()
}