package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductDetailResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface ProductService {
    @GET("Product/GetProducts")
    suspend fun getProductList(
        @Query(value = "categoryId") categoryId: Long,
        @HeaderMap header: HashMap<String, String>
    ): ApiResponse<ProductResponse>

    @GET("Product/GetProductDetail")
    suspend fun getProductDetail(
        @Query(value = "productId") productId: Long,
        @HeaderMap header: HashMap<String, String>
    ): ApiResponse<ProductDetailResponse>
}
