package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productService: ProductService) {

    suspend fun getProductList(categoryId: Int) = withContext(Dispatchers.IO) {
        productService.getProductList(
            categoryId = categoryId,
            header = RestConstants.getAuthHeader()
        )
    }

    suspend fun getProductDetail(productId: Int) = withContext(Dispatchers.IO) {
        productService.getProductDetail(
            productId = productId,
            header = RestConstants.getAuthHeader()
        )
    }
}