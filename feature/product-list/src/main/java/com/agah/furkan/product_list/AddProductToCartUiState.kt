package com.agah.furkan.product_list

sealed class AddProductToCartUiState {
    object Success : AddProductToCartUiState()
    object Loading : AddProductToCartUiState()
    data class Error(val message: String) : AddProductToCartUiState()
}