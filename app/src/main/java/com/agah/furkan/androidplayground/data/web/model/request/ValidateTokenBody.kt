package com.agah.furkan.androidplayground.data.web.model.request

import com.squareup.moshi.Json

data class ValidateTokenBody(
    @Json(name = "token")
    val token: String
)