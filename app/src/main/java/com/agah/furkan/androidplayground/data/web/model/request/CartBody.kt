package com.agah.furkan.androidplayground.data.web.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartBody(
    @Json(name = "Username")
    val username: String
)