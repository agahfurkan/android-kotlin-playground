package com.agah.furkan.androidplayground.data.web.model.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoveProductFromCartBody(
    @Json(name = "productId")
    val productId: Int,
    @Json(name = "userId")
    val userId: Int
)