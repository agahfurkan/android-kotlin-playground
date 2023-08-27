package com.agah.furkan.core.data.model

import com.squareup.moshi.Json

open class BaseResponse {
    @Json(name = "isSuccess")
    var isSuccess: Boolean = false

    @Json(name = "message")
    var message: String? = null
}
