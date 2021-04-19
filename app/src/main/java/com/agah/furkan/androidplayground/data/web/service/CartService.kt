package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.request.CartBody
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import java.util.*

interface CartService {

    @POST("Cart/getusercart")
    suspend fun getCart(
        @Body cartBody: CartBody,
        @HeaderMap header: HashMap<String, String>
    ): List<CartResponse>

}
