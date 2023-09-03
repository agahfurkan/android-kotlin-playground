package com.agah.furkan.data.cart

import com.agah.furkan.data.cart.remote.model.request.AddProductToCartBody
import com.agah.furkan.data.cart.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.data.cart.remote.model.response.CartResponse
import com.agah.furkan.core.data.model.Result

interface CartRepository {
    suspend fun getCart(
        refresh: Boolean = false,
        userId: Long
    ): Result<List<CartResponse.Cart>>

    suspend fun addProductToCart(addProductToCartBody: AddProductToCartBody): Result<String>
    suspend fun removeProductFromCart(removeProductFromCartBody: RemoveProductFromCartBody): Result<String>
}
