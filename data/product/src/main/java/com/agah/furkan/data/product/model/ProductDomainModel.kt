package com.agah.furkan.data.product.model

import com.agah.furkan.data.product.remote.model.response.ProductResponse

data class ProductDomainModel(
    val categoryId: Long,
    val discount: Double,
    val picture: String,
    val price: Double,
    val productDescription: String,
    val productId: Long,
    val productName: String
)

fun ProductResponse.Product.asDomainModel() =
    ProductDomainModel(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )