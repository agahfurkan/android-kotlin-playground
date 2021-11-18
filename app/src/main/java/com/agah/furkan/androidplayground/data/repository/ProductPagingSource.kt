package com.agah.furkan.androidplayground.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.service.ProductService
import okio.IOException
import retrofit2.HttpException

const val PRODUCT_PAGE_SIZE = 50
const val INITIAL_PAGE_INDEX = 0

class ProductPagingSource(
    private val productService: ProductService,
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
            val response = productService.getProductList(
                categoryId = categoryId,
                pageIndex = pageIndex,
                pageLength = PRODUCT_PAGE_SIZE,
                header = RestConstants.getAuthHeader()
            )
            val nextPageIndex =
                if (response is ApiSuccessResponse && response.data.productList.isNotEmpty()) {
                    pageIndex + 1
                } else {
                    null
                }
            LoadResult.Page(
                data = (response as ApiSuccessResponse).data.productList.map { it.toDomainModel() },
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = nextPageIndex
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
