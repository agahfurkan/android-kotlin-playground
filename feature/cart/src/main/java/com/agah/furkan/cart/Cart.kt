package com.agah.furkan.cart

internal data class Cart(
    val cartId: Long,
    val discount: Double,
    val picture: String,
    val price: Double,
    val productDescription: String,
    val productId: Long,
    val productName: String
)
