package com.agah.furkan.data.cart.remote.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoveProductFromCartBody(
    @Json(name = "productId")
    val productId: Long,
    @Json(name = "userId")
    val userId: Long
)
