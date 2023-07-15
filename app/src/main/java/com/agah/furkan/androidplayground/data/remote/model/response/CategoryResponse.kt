package com.agah.furkan.androidplayground.data.remote.model.response

import com.agah.furkan.data.model.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "categoryList")
    val categoryList: List<Category>
) : BaseResponse() {
    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "categoryId")
        val categoryId: Long,
        @Json(name = "categoryName")
        val categoryName: String
    )
}
