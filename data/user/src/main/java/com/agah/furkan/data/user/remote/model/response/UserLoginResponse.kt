package com.agah.furkan.data.user.remote.model.response

import com.agah.furkan.core.data.model.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLoginResponse(
    @Json(name = "token")
    val token: String?,
    @Json(name = "userId")
    val userId: Long?
) : BaseResponse()
