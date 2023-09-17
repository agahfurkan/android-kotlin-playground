package com.agah.furkan.data.cart

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.data.cart.model.CartDomainModel
import com.agah.furkan.data.cart.model.asDomainModel
import com.agah.furkan.data.cart.remote.CartService
import com.agah.furkan.data.cart.remote.model.request.AddProductToCartBody
import com.agah.furkan.data.cart.remote.model.request.RemoveProductFromCartBody
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

    private var cartCache: Result<List<CartDomainModel>>? = null

    @Inject
    constructor(
        cartService: CartService,
        errorMapper: ErrorMapper
    ) : this(cartService, errorMapper, Dispatchers.IO)

    override suspend fun getCart(refresh: Boolean, userId: Long): Result<List<CartDomainModel>> {
        if (refresh || cartCache == null) {
            val result = suspendCall(
                coroutineContext = coroutineContext,
                errorMapper = errorMapper,
                mapOnSuccess = { response -> response.cartList.asDomainModel() }
            ) {
                cartService.getCart(userId)
            }
            if (result is Result.Success) {
                cartCacheMutex.withLock {
                    this.cartCache = result
                }
            } else {
                return result
            }
        }

        return cartCache!!
    }


    override suspend fun addProductToCart(productId: Long, userId: Long): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.addProductToCart(AddProductToCartBody(productId, userId))
        }

    override suspend fun removeProductFromCart(productId: Long, userId: Long): Result<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.removeProductFromCart(RemoveProductFromCartBody(productId, userId))
        }
}
