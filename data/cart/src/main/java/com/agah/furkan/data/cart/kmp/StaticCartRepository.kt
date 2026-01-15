package com.agah.furkan.data.cart.kmp

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.cart.Cart
import com.agah.furkan.domain.cart.CartRepository
import com.agah.furkan.playgrounddatamodule.KmpCart
import com.agah.furkan.playgrounddatamodule.StaticCartData
import kotlinx.coroutines.delay
import javax.inject.Inject

class StaticCartRepository @Inject constructor() : CartRepository {

    override suspend fun getCart(refresh: Boolean, userId: Long): DomainResult<List<Cart>> {
        // Simulate network delay
        delay(400)

        val cartItems = StaticCartData.getCartForUser(userId)

        return DomainResult.Success(
            cartItems.map { it.toDomainCart() }
        )
    }

    override suspend fun addProductToCart(productId: Long, userId: Long): DomainResult<String> {
        // Simulate network delay
        delay(300)

        val success = StaticCartData.addProductToCart(userId, productId)

        return if (success) {
            DomainResult.Success("Product added to cart successfully")
        } else {
            DomainResult.Failure(
                com.agah.furkan.core.domain.model.DomainError.Unknown(
                    message = "Failed to add product to cart"
                )
            )
        }
    }

    override suspend fun removeProductFromCart(productId: Long, userId: Long): DomainResult<String> {
        // Simulate network delay
        delay(300)

        val success = StaticCartData.removeProductFromCart(userId, productId)

        return if (success) {
            DomainResult.Success("Product removed from cart successfully")
        } else {
            DomainResult.Failure(
                com.agah.furkan.core.domain.model.DomainError.Unknown(
                    message = "Failed to remove product from cart"
                )
            )
        }
    }

    // Mapper function
    private fun KmpCart.toDomainCart() = Cart(
        cartId = cartId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )
}

