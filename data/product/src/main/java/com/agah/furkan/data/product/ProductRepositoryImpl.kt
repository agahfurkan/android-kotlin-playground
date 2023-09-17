package com.agah.furkan.data.product

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.data.product.model.ProductDetailDomainModel
import com.agah.furkan.data.product.model.ProductDomainModel
import com.agah.furkan.data.product.model.asDomainModel
import com.agah.furkan.data.product.remote.ProductService
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
    ): Result<List<ProductDomainModel>> {
        return suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.productList.map { it.asDomainModel() } }
        ) {
            productService.getProductList(
                categoryId = categoryId,
                pageIndex = pageIndex,
                pageLength = pageLength
            )
        }
    }

    override suspend fun getProductDetail(productId: Long): Result<ProductDetailDomainModel> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.productDetail.asDomainModel() }
        ) {
            productService.getProductDetail(productId = productId)
        }
}
