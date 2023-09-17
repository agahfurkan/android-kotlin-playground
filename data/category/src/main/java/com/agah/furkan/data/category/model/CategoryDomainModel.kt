package com.agah.furkan.data.category.model

import com.agah.furkan.data.category.remote.model.response.CategoryResponse

data class CategoryDomainModel(
    val categoryId: Long,
    val categoryName: String
)

fun CategoryResponse.Category.asDomainModel() = CategoryDomainModel(
    categoryId = categoryId,
    categoryName = categoryName
)