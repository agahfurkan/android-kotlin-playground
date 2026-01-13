package com.agah.furkan.data.category.remote.model.response

import com.agah.furkan.core.data.model.BaseResponse
import com.agah.furkan.domain.category.Category
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "categoryList")
    val categoryList: List<CategoryDto>
) : BaseResponse() {
    @JsonClass(generateAdapter = true)
    data class CategoryDto(
        @Json(name = "categoryId")
        val categoryId: Long,
        @Json(name = "categoryName")
        val categoryName: String
    )
}

fun CategoryResponse.CategoryDto.asDomainModel() = Category(
    categoryId = categoryId,
    categoryName = categoryName
)

