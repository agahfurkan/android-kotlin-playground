package com.agah.furkan.cart

import com.agah.furkan.cart.remote.CartService
import com.agah.furkan.cart.remote.model.request.AddProductToCartBody
import com.agah.furkan.cart.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.cart.remote.model.response.CartResponse
import com.agah.furkan.data.ErrorMapper
import com.agah.furkan.data.suspendCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CartRepositoryImpl(
    private val cartService: CartService,
    private val errorMapper: ErrorMapper,
    private val coroutineContext: CoroutineContext
) : CartRepository {
    @Inject
    constructor(
        cartService: CartService,
        errorMapper: ErrorMapper
    ) : this(cartService, errorMapper, Dispatchers.IO)

    override suspend fun fetchCart(userId: Long): com.agah.furkan.data.model.Result<List<CartResponse.Cart>> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.cartList }
        ) {
            cartService.getCart(userId)
        }

    override suspend fun addProductToCart(addProductToCartBody: AddProductToCartBody): com.agah.furkan.data.model.Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.addProductToCart(addProductToCartBody)
        }

    override suspend fun removeProductFromCart(removeProductFromCartBody: RemoveProductFromCartBody): com.agah.furkan.data.model.Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.removeProductFromCart(removeProductFromCartBody)
        }
}
