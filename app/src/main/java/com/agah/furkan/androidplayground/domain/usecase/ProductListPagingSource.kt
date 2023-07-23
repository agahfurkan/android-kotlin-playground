package com.agah.furkan.androidplayground.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.agah.furkan.androidplayground.data.repository.PRODUCT_PAGE_SIZE
import com.agah.furkan.androidplayground.data.repository.ProductPagingSource
import com.agah.furkan.product.ProductRepository
import com.agah.furkan.product.remote.model.response.ProductResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListPagingSource @Inject constructor(private val productRepository: ProductRepository) {

    fun getProductList(categoryId: Long): Flow<PagingData<ProductResponse.Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = PRODUCT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingSource(
                    productRepository = productRepository,
                    categoryId = categoryId,
                )
            }
        ).flow
    }
}