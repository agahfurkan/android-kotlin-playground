package com.agah.furkan.user.remote.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValidateTokenBody(
    @Json(name = "token")
    val token: String
)
