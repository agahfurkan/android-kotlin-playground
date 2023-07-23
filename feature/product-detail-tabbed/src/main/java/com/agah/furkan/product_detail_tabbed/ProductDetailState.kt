package com.agah.furkan.product_detail_tabbed

import com.agah.furkan.product.ProductDetail

sealed class ProductDetailState {
    data class Success(val data: ProductDetail) : ProductDetailState()
    object Loading : ProductDetailState()
}