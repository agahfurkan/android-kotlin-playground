package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.web.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.data.web.service.CartService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CartRepository @Inject constructor(private val cartService: CartService) {

    suspend fun getCart(userId: Int) = withContext(Dispatchers.IO) {
        cartService.getCart(userId, RestConstants.getAuthHeader())
    }

    suspend fun addProductToCart(addProductToCartBody: AddProductToCartBody) =
        withContext(Dispatchers.IO) {
            cartService.addProductToCart(addProductToCartBody, RestConstants.getAuthHeader())
        }

    suspend fun removeProductFromCart(removeProductFromCartBody: RemoveProductFromCartBody) =
        withContext(Dispatchers.IO) {
            cartService.removeProductFromCart(
                removeProductFromCartBody,
                RestConstants.getAuthHeader()
            )
        }
}
