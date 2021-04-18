package com.agah.furkan.androidplayground.data.web.model.response

import com.squareup.moshi.Json

data class CategoryResponse(
    @Json(name = "categoryId")
    val categoryId: Int,
    @Json(name = "categoryName")
    val categoryName: String
)