package com.agah.furkan.domain.product

import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun getProductDetails(): Flow<ProductDetailFlow>
}

