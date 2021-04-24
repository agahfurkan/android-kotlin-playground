package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query
import java.util.*

interface ProductService {

    @GET("Product/GetProducts")
    suspend fun getProductList(
        @Query(value = "categoryId") categoryId: Int,
        @HeaderMap header: HashMap<String, String>
    ): ApiResponse<List<ProductResponse>>

}