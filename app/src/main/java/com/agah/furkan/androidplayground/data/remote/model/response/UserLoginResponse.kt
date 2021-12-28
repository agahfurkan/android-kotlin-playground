package com.agah.furkan.androidplayground.data.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLoginResponse(
    @Json(name = "token")
    val token: String?,
    @Json(name = "userId")
    val userId: Long?
) : BaseResponse()
