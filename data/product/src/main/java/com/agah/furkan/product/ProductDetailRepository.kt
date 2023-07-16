package com.agah.furkan.product

import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun getProductDetails(): Flow<ProductDetail>
}