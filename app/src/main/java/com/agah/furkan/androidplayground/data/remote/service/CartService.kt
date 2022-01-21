package com.agah.furkan.androidplayground.data.remote.service

import com.agah.furkan.androidplayground.data.remote.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.data.remote.model.response.AddProductToCartResponse
import com.agah.furkan.androidplayground.data.remote.model.response.CartResponse
import com.agah.furkan.androidplayground.data.remote.model.response.RemoveProductFromCartResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface CartService {

    @POST("Cart/GetUserCart")
    suspend fun getCart(
        @Query("userId") userId: Long
    ): CartResponse

    @POST("Cart/AddProductToCart")
    suspend fun addProductToCart(
        @Body addProductToCartBody: AddProductToCartBody
    ): AddProductToCartResponse

    @POST("Cart/RemoveProductFromCart")
    suspend fun removeProductFromCart(
        @Body removeProductFromCartBody: RemoveProductFromCartBody
    ): RemoveProductFromCartResponse
}
