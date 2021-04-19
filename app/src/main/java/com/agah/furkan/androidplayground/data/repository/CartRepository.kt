package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.model.request.CartBody
import com.agah.furkan.androidplayground.data.web.service.CartService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(private val cartService: CartService) {

    suspend fun getCart(cartBody: CartBody) = withContext(Dispatchers.IO) {
        cartService.getCart(cartBody, RestConstants.getAuthHeader())
    }
}