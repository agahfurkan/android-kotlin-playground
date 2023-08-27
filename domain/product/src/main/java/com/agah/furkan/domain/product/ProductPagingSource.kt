package com.agah.furkan.domain.product

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.product.ProductRepository

const val PRODUCT_PAGE_SIZE = 50
const val INITIAL_PAGE_INDEX = 0

class ProductPagingSource(
    private val productRepository: ProductRepository,
    private val categoryId: Long
) : PagingSource<Int, Product>() {


    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val pageIndex = params.key ?: INITIAL_PAGE_INDEX
        return try {
            val response = productRepository.getProductList(
                categoryId = categoryId,
                pageIndex = pageIndex,
                pageLength = PRODUCT_PAGE_SIZE
            )

            val nextPageIndex =
                if (response is Result.Success && response.data.productList.isNotEmpty()) {
                    pageIndex + 1
                } else {
                    null
                }
            LoadResult.Page(
                data = (response as Result.Success).data.productList.map {
                    Product(
                        categoryId = it.categoryId,
                        discount = it.discount,
                        picture = it.picture,
                        price = it.price,
                        productDescription = it.productDescription,
                        productId = it.productId,
                        productName = it.productName
                    )
                },
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = nextPageIndex
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
