package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productService: ProductService) {

    suspend fun getProductList(categoryId: Long): List<Product> = withContext(Dispatchers.IO) {
        return@withContext when (
            val response = productService.getProductList(
                categoryId = categoryId,
                header = RestConstants.getAuthHeader()
            )
        ) {
            is ApiSuccessResponse -> {
                response.data.productList.map { it.toDomainModel() }
            }
            is ApiErrorResponse -> {
                listOf()
            }
        }
    }

    suspend fun getProductDetail(productId: Long) = withContext(Dispatchers.IO) {
        productService.getProductDetail(
            productId = productId,
            header = RestConstants.getAuthHeader()
        )
    }
}
