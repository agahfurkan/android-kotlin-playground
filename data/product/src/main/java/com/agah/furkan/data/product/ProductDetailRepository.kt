package com.agah.furkan.data.product

import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun getProductDetails(): Flow<ProductDetail>
}