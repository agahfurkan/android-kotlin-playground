package com.agah.furkan.data.product

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.data.product.remote.ProductService
import com.agah.furkan.data.product.remote.model.response.ProductDetailResponse
import com.agah.furkan.data.product.remote.model.response.ProductResponse
import com.agah.furkan.domain.product.Product
import com.agah.furkan.domain.product.ProductDetail
import com.agah.furkan.domain.product.ProductRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProductRepositoryImpl(
    private val productService: ProductService,
    private val errorMapper: ErrorMapper,
    private val coroutineContext: CoroutineContext
) : ProductRepository {
    @Inject
    constructor(
        productService: ProductService,
        errorMapper: ErrorMapper
    ) : this(productService, errorMapper, Dispatchers.IO)

    override suspend fun getProductList(
        categoryId: Long,
        pageIndex: Int,
        pageLength: Int
    ): DomainResult<List<Product>> {
        return suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.productList.map { it.toDomain() } }
        ) {
            productService.getProductList(
                categoryId = categoryId,
                pageIndex = pageIndex,
                pageLength = pageLength
            )
        }
    }

    override suspend fun getProductDetail(productId: Long): DomainResult<ProductDetail> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.productDetail.toDomain() }
        ) {
            productService.getProductDetail(productId = productId)
        }

    private fun ProductResponse.Product.toDomain() = Product(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )

    private fun ProductDetailResponse.ProductDetail.toDomain() = ProductDetail(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )
}
