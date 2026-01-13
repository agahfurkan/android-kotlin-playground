package com.agah.furkan.domain.product

import com.agah.furkan.core.domain.model.DomainResult
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: Long): DomainResult<ProductDetail> {
        return productRepository.getProductDetail(productId)
    }
}

