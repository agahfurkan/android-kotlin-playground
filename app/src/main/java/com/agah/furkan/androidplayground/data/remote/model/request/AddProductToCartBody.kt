package com.agah.furkan.androidplayground.data.remote.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProductToCartBody(
    @Json(name = "productId")
    val productId: Long,
    @Json(name = "userId")
    val userId: Long,
    @Json(name = "discount")
    val discount: Double,
    @Json(name = "picture")
    val picture: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "productDescription")
    val productDescription: String,
    @Json(name = "productName")
    val productName: String
)
