package com.agah.furkan.domain.product

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListPagingSource @Inject constructor(private val productRepository: ProductRepository) {

    fun getProductList(categoryId: Long): Flow<PagingData<Product>> {
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