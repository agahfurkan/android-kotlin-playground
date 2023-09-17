package com.agah.furkan.data.cart.model

import com.agah.furkan.data.cart.remote.model.response.CartResponse

data class CartDomainModel(
    val cartId: Long,
    val discount: Double,
    val picture: String,
    val price: Double,
    val productDescription: String,
    val productId: Long,
    val productName: String
)

fun List<CartResponse.Cart>.asDomainModel(): List<CartDomainModel> {
    return map {
        CartDomainModel(
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