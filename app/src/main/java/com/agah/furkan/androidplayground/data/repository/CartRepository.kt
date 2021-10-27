package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.domain.model.Cart
import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.web.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.data.web.service.CartService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(private val cartService: CartService) {

    suspend fun getCart(userId: Long): List<Cart> = withContext(Dispatchers.IO) {
        return@withContext when (
            val response =
                cartService.getCart(userId, RestConstants.getAuthHeader())
        ) {
            is ApiSuccessResponse -> {
                response.data.cartList?.map { it.toDomainModel() } ?: listOf()
            }
            is ApiErrorResponse -> {
                listOf()
            }
        }
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
