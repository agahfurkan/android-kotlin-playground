package com.agah.furkan.androidplayground.data.web.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "categoryList")
    val categoryList: List<Category>?
) : GenericResponse() {
    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "categoryId")
        val categoryId: Int,
        @Json(name = "categoryName")
        val categoryName: String
    )
}
