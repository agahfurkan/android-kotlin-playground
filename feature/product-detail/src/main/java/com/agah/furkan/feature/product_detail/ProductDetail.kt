package com.agah.furkan.feature.product_detail

import com.agah.furkan.data.product.model.ProductDetailDomainModel

data class ProductDetail(
    val categoryId: Int,
    val discount: Double,
    val picture: String,
    val price: Double,
    val productDescription: String,
    val productId: Int,
    val productName: String
)

fun ProductDetailDomainModel.asUiModel() =
    ProductDetail(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )