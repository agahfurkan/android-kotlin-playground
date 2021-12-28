package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.androidplayground.data.remote.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Cart

interface CartRepository {
    suspend fun fetchCart(userId: Long): Result<List<Cart>>
    suspend fun addProductToCart(addProductToCartBody: AddProductToCartBody): Result<String>
    suspend fun removeProductFromCart(removeProductFromCartBody: RemoveProductFromCartBody): Result<String>
}
