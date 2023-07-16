package com.agah.furkan.product

import com.agah.furkan.data.ErrorMapper
import com.agah.furkan.data.model.Result
import com.agah.furkan.product.remote.ProductService
import com.agah.furkan.product.remote.model.response.ProductDetailResponse
import com.agah.furkan.product.remote.model.response.ProductResponse
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
    ): Result<ProductResponse> {
        return com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response }
        ) {
            productService.getProductList(
                categoryId = categoryId,
                pageIndex = pageIndex,
                pageLength = pageLength
            )
        }
    }

    override suspend fun getProductDetail(productId: Long): Result<ProductDetailResponse> =
        com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response }
        ) {
            productService.getProductDetail(productId = productId)
        }
}
