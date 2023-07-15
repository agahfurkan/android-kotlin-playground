package com.agah.furkan.cart

import com.agah.furkan.cart.remote.model.request.AddProductToCartBody
import com.agah.furkan.cart.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.cart.remote.model.response.CartResponse

interface CartRepository {
    suspend fun fetchCart(userId: Long): com.agah.furkan.data.model.Result<List<CartResponse.Cart>>
    suspend fun addProductToCart(addProductToCartBody: AddProductToCartBody): com.agah.furkan.data.model.Result<String>
    suspend fun removeProductFromCart(removeProductFromCartBody: RemoveProductFromCartBody): com.agah.furkan.data.model.Result<String>
}
