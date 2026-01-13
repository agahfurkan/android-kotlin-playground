package com.agah.furkan.domain.cart

import com.agah.furkan.core.domain.model.DomainResult

interface CartRepository {
    suspend fun getCart(
        refresh: Boolean = false,
        userId: Long
    ): DomainResult<List<Cart>>

    suspend fun addProductToCart(productId: Long, userId: Long): DomainResult<String>
    suspend fun removeProductFromCart(productId: Long, userId: Long): DomainResult<String>
}

