package com.agah.furkan.data.product

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.product.remote.ProductService
import com.agah.furkan.data.product.remote.model.response.ProductDetailResponse
import com.agah.furkan.data.product.remote.model.response.ProductResponse
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
        return suspendCall(
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
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response }
        ) {
            productService.getProductDetail(productId = productId)
        }
}
