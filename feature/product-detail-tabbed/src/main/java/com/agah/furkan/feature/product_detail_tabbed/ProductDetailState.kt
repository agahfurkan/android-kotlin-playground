package com.agah.furkan.feature.product_detail_tabbed

import com.agah.furkan.domain.product.ProductDetailFlow

internal sealed class ProductDetailState {
    data class Success(val data: ProductDetailFlow) : ProductDetailState()
    object Loading : ProductDetailState()
}