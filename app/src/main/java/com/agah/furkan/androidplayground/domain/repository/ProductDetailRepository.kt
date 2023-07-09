package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.androidplayground.data.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun getProductDetails(): Flow<ProductDetail>
}