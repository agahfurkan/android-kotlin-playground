package com.agah.furkan.androidplayground.data.web.model.response

import com.agah.furkan.androidplayground.data.domain.DomainModelConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.agah.furkan.androidplayground.data.domain.model.Category as CategoryDomainModel

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "categoryList")
    val categoryList: List<Category>?
) : GenericResponse() {
    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "categoryId")
        val categoryId: Long,
        @Json(name = "categoryName")
        val categoryName: String
    ) : DomainModelConverter<CategoryDomainModel> {
        override fun toDomainModel(): CategoryDomainModel {
            return CategoryDomainModel(categoryId = categoryId, categoryName = categoryName)
        }
    }
}
