package com.agah.furkan.androidplayground.domain.model.result

import com.agah.furkan.androidplayground.base.ListModel

data class Product(
    val categoryId: Long,
    val discount: Double,
    val picture: String,
    val price: Double,
    val productDescription: String,
    val productId: Long,
    val productName: String
) : ListModel {
    override val id: Long
        get() = productId
}
