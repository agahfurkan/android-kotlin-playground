package com.agah.furkan.domain.product

data class Product(
    val categoryId: Long,
    val discount: Double,
    val picture: String,
    val price: Double,
    val productDescription: String,
    val productId: Long,
    val productName: String
)