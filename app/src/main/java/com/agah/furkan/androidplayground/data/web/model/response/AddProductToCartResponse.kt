package com.agah.furkan.androidplayground.data.web.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProductToCartResponse(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "message")
    val message: String?
)
