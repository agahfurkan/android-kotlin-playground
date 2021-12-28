package com.agah.furkan.androidplayground.data.remote.model.response

import com.squareup.moshi.Json

open class BaseResponse {
    @Json(name = "isSuccess")
    var isSuccess: Boolean = false

    @Json(name = "message")
    var message: String? = null
}
