package com.agah.furkan.androidplayground.data.web.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRegisterBody(
    @Json(name = "password")
    val password: String?,
    @Json(name = "username")
    val username: String?
)
