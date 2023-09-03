package com.agah.furkan.data.product

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.product.remote.model.response.ProductDetailResponse
import com.agah.furkan.data.product.remote.model.response.ProductResponse

interface ProductRepository {
    suspend fun getProductList(
        categoryId: Long,
        pageIndex: Int,
        pageLength: Int
    ): Result<ProductResponse>

    suspend fun getProductDetail(productId: Long): Result<ProductDetailResponse>
}
