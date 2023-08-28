package com.agah.furkan.feature.product_detail

import com.agah.furkan.data.product.remote.model.response.ProductDetailResponse


internal sealed class ProductDetailUiState {
    data class Success(val productDetail: ProductDetailResponse.ProductDetail) :
        ProductDetailUiState()

    object Loading : ProductDetailUiState()
    data class Error(val errorMessage: String) : ProductDetailUiState()
}