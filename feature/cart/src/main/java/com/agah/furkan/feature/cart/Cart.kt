package com.agah.furkan.feature.cart

import com.agah.furkan.data.cart.model.CartDomainModel

data class Cart(
    val cartId: Long,
    val discount: Double,
    val picture: String,
    val price: Double,
    val productDescription: String,
    val productId: Long,
    val productName: String
)

fun List<CartDomainModel>.toUiModel(): List<Cart> {
    return this.map {
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