package com.agah.furkan.androidplayground.ui.productdetail

import com.agah.furkan.androidplayground.domain.model.result.ProductDetail


sealed class ProductDetailUiState {
    data class Success(val productDetail: ProductDetail) : ProductDetailUiState()
    object Loading : ProductDetailUiState()
    data class Error(val errorMessage: String) : ProductDetailUiState()
}