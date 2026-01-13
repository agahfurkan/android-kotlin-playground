package com.agah.furkan.domain.product

import com.agah.furkan.core.domain.model.DomainResult

interface ProductRepository {
    suspend fun getProductList(
        categoryId: Long,
        pageIndex: Int,
        pageLength: Int
    ): DomainResult<List<Product>>

    suspend fun getProductDetail(productId: Long): DomainResult<ProductDetail>
}

