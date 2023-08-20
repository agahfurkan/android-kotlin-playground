package com.agah.furkan.cart

import com.agah.furkan.cart.remote.CartService
import com.agah.furkan.cart.remote.model.request.AddProductToCartBody
import com.agah.furkan.cart.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.cart.remote.model.response.CartResponse
import com.agah.furkan.data.ErrorMapper
import com.agah.furkan.data.model.Result
import com.agah.furkan.data.suspendCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CartRepositoryImpl(
    private val cartService: CartService,
    private val errorMapper: ErrorMapper,
    private val coroutineContext: CoroutineContext
) : CartRepository {
    private val cartCacheMutex = Mutex()

    private var cartCache: Result<List<CartResponse.Cart>>? = null

    @Inject
    constructor(
        cartService: CartService,
        errorMapper: ErrorMapper
    ) : this(cartService, errorMapper, Dispatchers.IO)

    override suspend fun getCart(refresh: Boolean, userId: Long): Result<List<CartResponse.Cart>> {
        if (refresh || cartCache == null) {
            val result = suspendCall(
                coroutineContext = coroutineContext,
                errorMapper = errorMapper,
                mapOnSuccess = { response -> response.cartList }
            ) {
                cartService.getCart(userId)
            }
            if (result is Result.Success) {
                cartCacheMutex.withLock {
                    this.cartCache = result
                }
            }
        }

        return cartCache!!
    }


    override suspend fun addProductToCart(addProductToCartBody: AddProductToCartBody): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.addProductToCart(addProductToCartBody)
        }

    override suspend fun removeProductFromCart(removeProductFromCartBody: RemoveProductFromCartBody): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.removeProductFromCart(removeProductFromCartBody)
        }
}
