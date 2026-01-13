package com.agah.furkan.data.cart

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.data.cart.remote.CartService
import com.agah.furkan.data.cart.remote.model.request.AddProductToCartBody
import com.agah.furkan.data.cart.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.data.cart.remote.model.response.CartResponse
import com.agah.furkan.domain.cart.Cart
import com.agah.furkan.domain.cart.CartRepository
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

    private var cartCache: DomainResult<List<Cart>>? = null

    @Inject
    constructor(
        cartService: CartService,
        errorMapper: ErrorMapper
    ) : this(cartService, errorMapper, Dispatchers.IO)

    override suspend fun getCart(refresh: Boolean, userId: Long): DomainResult<List<Cart>> {
        if (refresh || cartCache == null) {
            val result = suspendCall(
                coroutineContext = coroutineContext,
                errorMapper = errorMapper,
                mapOnSuccess = { response -> response.cartList.toDomain() }
            ) {
                cartService.getCart(userId)
            }
            if (result is DomainResult.Success) {
                cartCacheMutex.withLock {
                    this.cartCache = result
                }
            } else {
                return result
            }
        }

        return cartCache!!
    }


    override suspend fun addProductToCart(productId: Long, userId: Long): DomainResult<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.addProductToCart(AddProductToCartBody(productId, userId))
        }

    override suspend fun removeProductFromCart(productId: Long, userId: Long): DomainResult<String> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { it.message ?: "" }
        ) {
            cartService.removeProductFromCart(RemoveProductFromCartBody(productId, userId))
        }

    private fun List<CartResponse.Cart>.toDomain(): List<Cart> {
        return map {
            Cart(
                cartId = it.cartId,
                discount = it.discount,
                picture = it.picture,
                price = it.price,
                productDescription = it.productDescription,
                productId = it.productId,
                productName = it.productName
            )
        }
    }
}
