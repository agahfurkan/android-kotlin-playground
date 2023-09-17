package com.agah.furkan.data.cart

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.cart.model.CartDomainModel

interface CartRepository {
    suspend fun getCart(
        refresh: Boolean = false,
        userId: Long
    ): Result<List<CartDomainModel>>

    suspend fun addProductToCart(productId: Long, userId: Long): Result<String>
    suspend fun removeProductFromCart(productId: Long, userId: Long): Result<String>
}
