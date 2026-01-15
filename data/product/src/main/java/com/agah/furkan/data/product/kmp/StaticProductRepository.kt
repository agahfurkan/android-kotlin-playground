package com.agah.furkan.data.product.kmp

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.product.Product
import com.agah.furkan.domain.product.ProductDetail
import com.agah.furkan.domain.product.ProductRepository
import com.agah.furkan.playgrounddatamodule.KmpProduct
import com.agah.furkan.playgrounddatamodule.KmpProductDetail
import com.agah.furkan.playgrounddatamodule.StaticProductData
import kotlinx.coroutines.delay
import javax.inject.Inject

class StaticProductRepository @Inject constructor() : ProductRepository {

    override suspend fun getProductList(
        categoryId: Long,
        pageIndex: Int,
        pageLength: Int
    ): DomainResult<List<Product>> {
        // Simulate network delay
        delay(500)

        val staticProducts = StaticProductData.getProductsPaginated(
            categoryId = categoryId,
            pageIndex = pageIndex,
            pageLength = pageLength
        )

        return DomainResult.Success(
            staticProducts.map { it.toDomainProduct() }
        )
    }

    override suspend fun getProductDetail(productId: Long): DomainResult<ProductDetail> {
        // Simulate network delay
        delay(300)

        val staticProduct = StaticProductData.getProductById(productId)

        return if (staticProduct != null) {
            DomainResult.Success(staticProduct.toDomainProductDetail())
        } else {
            DomainResult.Failure(
                com.agah.furkan.core.domain.model.DomainError.NotFound(
                    message = "Product not found with id: $productId"
                )
            )
        }
    }

    // Mapper functions - these will be part of KMP shared module
    private fun KmpProduct.toDomainProduct() = Product(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )

    private fun KmpProductDetail.toDomainProductDetail() = ProductDetail(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )
}

