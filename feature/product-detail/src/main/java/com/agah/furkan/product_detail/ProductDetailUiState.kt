package com.agah.furkan.product_detail

import com.agah.furkan.product.remote.model.response.ProductDetailResponse


sealed class ProductDetailUiState {
    data class Success(val productDetail: ProductDetailResponse.ProductDetail) :
        ProductDetailUiState()

    object Loading : ProductDetailUiState()
    data class Error(val errorMessage: String) : ProductDetailUiState()
}