package com.agah.furkan.androidplayground.data.remote.service

import com.agah.furkan.androidplayground.data.remote.model.response.ProductDetailResponse
import com.agah.furkan.androidplayground.data.remote.model.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("Product/GetProducts")
    suspend fun getProductList(
        @Query(value = "categoryId") categoryId: Long,
        @Query(value = "pageIndex") pageIndex: Int,
        @Query(value = "pageLength") pageLength: Int
    ): ProductResponse

    @GET("Product/GetProductDetail")
    suspend fun getProductDetail(
        @Query(value = "productId") productId: Long
    ): ProductDetailResponse
}
