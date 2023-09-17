package com.agah.furkan.data.product

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.product.model.ProductDetailDomainModel
import com.agah.furkan.data.product.model.ProductDomainModel

interface ProductRepository {
    suspend fun getProductList(
        categoryId: Long,
        pageIndex: Int,
        pageLength: Int
    ): Result<List<ProductDomainModel>>

    suspend fun getProductDetail(productId: Long): Result<ProductDetailDomainModel>
}
