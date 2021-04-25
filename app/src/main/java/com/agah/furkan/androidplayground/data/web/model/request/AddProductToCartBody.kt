package com.agah.furkan.androidplayground.data.web.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProductToCartBody(
    @Json(name = "productId")
    val productId: Int,
    @Json(name = "userId")
    val userId: Int
)