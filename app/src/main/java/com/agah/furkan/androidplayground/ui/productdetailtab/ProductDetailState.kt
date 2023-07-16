package com.agah.furkan.androidplayground.ui.productdetailtab

import com.agah.furkan.product.ProductDetail

sealed class ProductDetailState {
    data class Success(val data: ProductDetail) : ProductDetailState()
    object Loading : ProductDetailState()
}