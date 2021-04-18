package com.agah.furkan.androidplayground.data.web.model.response

import com.squareup.moshi.Json

data class ValidateTokenResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String
)