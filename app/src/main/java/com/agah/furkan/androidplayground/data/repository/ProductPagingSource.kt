package com.agah.furkan.androidplayground.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agah.furkan.product.ProductRepository
import com.agah.furkan.product.remote.model.response.ProductResponse
import okio.IOException
import retrofit2.HttpException

const val PRODUCT_PAGE_SIZE = 50
const val INITIAL_PAGE_INDEX = 0

class ProductPagingSource(
    private val productRepository: ProductRepository,
    private val categoryId: Long
) : PagingSource<Int, ProductResponse.Product>() {


    override fun getRefreshKey(state: PagingState<Int, ProductResponse.Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse.Product> {
        val pageIndex = params.key ?: INITIAL_PAGE_INDEX
        return try {
            val response = productRepository.getProductList(
                categoryId = categoryId,
                pageIndex = pageIndex,
                pageLength = PRODUCT_PAGE_SIZE
            )

            val nextPageIndex =
                if (response is com.agah.furkan.data.model.Result.Success && response.data.productList.isNotEmpty()) {
                    pageIndex + 1
                } else {
                    null
                }
            LoadResult.Page(
                data = (response as com.agah.furkan.data.model.Result.Success).data.productList,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = nextPageIndex
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
