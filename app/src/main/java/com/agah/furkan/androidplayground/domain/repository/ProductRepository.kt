package com.agah.furkan.androidplayground.domain.repository

import androidx.paging.PagingData
import com.agah.furkan.data.model.Result
import com.agah.furkan.androidplayground.domain.model.result.Product
import com.agah.furkan.androidplayground.domain.model.result.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductList(categoryId: Long): Flow<PagingData<Product>>
    suspend fun getProductDetail(productId: Long): com.agah.furkan.data.model.Result<ProductDetail>
}
