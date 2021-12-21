package com.agah.furkan.androidplayground.data.web.model.response

import com.squareup.moshi.Json

open class GenericResponse {
    @Json(name = "isSuccess")
    var isSuccess: Boolean = false

    @Json(name = "message")
    var message: String? = null
}
