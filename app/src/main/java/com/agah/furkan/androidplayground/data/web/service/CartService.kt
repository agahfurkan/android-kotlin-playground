package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.web.model.response.AddProductToCartResponse
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface CartService {

    @POST("Cart/GetUserCart")
    suspend fun getCart(
        @Query("userId") userId: Int,
        @HeaderMap header: HashMap<String, String>
    ): ApiResponse<List<CartResponse>>

    @POST("api/Cart/AddProductToCart")
    suspend fun addProductToCart(
        @Body addProductToCartBody: AddProductToCartBody,
        @HeaderMap header: HashMap<String, String>
    ): ApiResponse<AddProductToCartResponse>
}