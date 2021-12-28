package com.agah.furkan.androidplayground.data.remote.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLoginBody(
    @Json(name = "password")
    val password: String,
    @Json(name = "username")
    val username: String
)
