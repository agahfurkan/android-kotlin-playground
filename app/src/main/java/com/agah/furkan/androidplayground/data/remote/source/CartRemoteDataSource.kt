package com.agah.furkan.androidplayground.data.remote.source

import com.agah.furkan.androidplayground.data.remote.service.CartService

class CartRemoteDataSource constructor(private val service: CartService) {
    suspend fun getCart(userId: Long, header: HashMap<String, String>) =
        service.getCart(userId, header)
}
