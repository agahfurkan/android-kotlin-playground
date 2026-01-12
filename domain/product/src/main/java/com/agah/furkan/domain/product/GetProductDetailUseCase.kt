package com.agah.furkan.domain.product

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.product.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: Long): Result<ProductDetail> {
        return when (val result = productRepository.getProductDetail(productId)) {
            is Result.Success -> Result.Success(result.data.toDomain())
            is Result.Failure -> Result.Failure(result.error)
        }
    }

    private fun com.agah.furkan.data.product.model.ProductDetailDomainModel.toDomain(): ProductDetail {
        return ProductDetail(
            categoryId = categoryId,
            discount = discount,
            picture = picture,
            price = price,
            productDescription = productDescription,
            productId = productId,
            productName = productName
        )
    }
}

