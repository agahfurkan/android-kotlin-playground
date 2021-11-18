package com.agah.furkan.androidplayground.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productService: ProductService) {

    fun getProductList(
        categoryId: Long
    ): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = PRODUCT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingSource(
                    productService = productService,
                    categoryId = categoryId
                )
            }
        ).flow
    }

    suspend fun getProductDetail(productId: Long) = withContext(Dispatchers.IO) {
        productService.getProductDetail(
            productId = productId,
            header = RestConstants.getAuthHeader()
        )
    }
}
