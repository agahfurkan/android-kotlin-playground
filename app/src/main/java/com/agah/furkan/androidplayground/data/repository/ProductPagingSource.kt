package com.agah.furkan.androidplayground.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.data.remote.service.ProductService
import com.agah.furkan.androidplayground.domain.ErrorMapper
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Product
import com.agah.furkan.androidplayground.domain.util.suspendCall
import kotlinx.coroutines.Dispatchers
import okio.IOException
import retrofit2.HttpException

const val PRODUCT_PAGE_SIZE = 50
const val INITIAL_PAGE_INDEX = 0

class ProductPagingSource(
    private val productService: ProductService,
    private val categoryId: Long,
    private val errorMapper: ErrorMapper
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
            val response =
                suspendCall(coroutineContext = Dispatchers.IO, errorMapper = errorMapper, call = {
                    productService.getProductList(
                        categoryId = categoryId,
                        pageIndex = pageIndex,
                        pageLength = PRODUCT_PAGE_SIZE
                    )
                }, map = { response -> response.productList.map { it.toDomainModel() } })
            val nextPageIndex =
                if (response is Result.Success && response.data.isNotEmpty()) {
                    pageIndex + 1
                } else {
                    null
                }
            LoadResult.Page(
                data = (response as Result.Success).data,
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
