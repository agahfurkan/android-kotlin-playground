package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.data.remote.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.data.remote.service.CartService
import com.agah.furkan.androidplayground.domain.ErrorMapper
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.domain.repository.CartRepository
import com.agah.furkan.androidplayground.domain.util.suspendCall
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

    override suspend fun fetchCart(userId: Long): Result<List<Cart>> =
        suspendCall(
            coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response.cartList.map { it.toDomainModel() } }
        ) {
            cartService.getCart(userId)
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
