package com.agah.furkan.androidplayground.data.web.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponse(
    @Json(name = "productId")
    val productId: Int,
    @Json(name = "userId")
    val userId: Int
)