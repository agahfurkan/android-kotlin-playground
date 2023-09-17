package com.agah.furkan.feature.product_detail


internal sealed class ProductDetailUiState {
    data class Success(val productDetail: ProductDetail) : ProductDetailUiState()
    object Loading : ProductDetailUiState()
    data class Error(val errorMessage: String) : ProductDetailUiState()
}