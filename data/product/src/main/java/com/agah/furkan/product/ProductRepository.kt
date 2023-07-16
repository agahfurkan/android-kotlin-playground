package com.agah.furkan.product

import com.agah.furkan.product.remote.model.response.ProductDetailResponse
import com.agah.furkan.product.remote.model.response.ProductResponse

interface ProductRepository {
    suspend fun getProductList(
        categoryId: Long,
        pageIndex: Int,
        pageLength: Int
    ): com.agah.furkan.data.model.Result<ProductResponse>

    suspend fun getProductDetail(productId: Long): com.agah.furkan.data.model.Result<ProductDetailResponse>
}
