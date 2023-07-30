package com.agah.furkan.product_detail_tabbed

import com.agah.furkan.product.ProductDetail

internal sealed class ProductDetailState {
    data class Success(val data: ProductDetail) : ProductDetailState()
    object Loading : ProductDetailState()
}