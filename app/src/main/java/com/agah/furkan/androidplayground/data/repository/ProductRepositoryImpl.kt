package com.agah.furkan.androidplayground.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.data.remote.service.ProductService
import com.agah.furkan.androidplayground.domain.model.result.Product
import com.agah.furkan.androidplayground.domain.model.result.ProductDetail
import com.agah.furkan.androidplayground.domain.repository.ProductRepository
import com.agah.furkan.data.ErrorMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    override fun getProductList(
        categoryId: Long
    ): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = PRODUCT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingSource(
                    productService = productService,
                    categoryId = categoryId,
                    errorMapper = errorMapper
                )
            }
        ).flow
    }

    override suspend fun getProductDetail(productId: Long): com.agah.furkan.data.model.Result<ProductDetail> =
        com.agah.furkan.data.suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.productDetail.toDomainModel() }
        ) {
            productService.getProductDetail(productId = productId)
        }
}
