package com.agah.furkan.feature.category_list.model

import com.agah.furkan.data.category.model.CategoryDomainModel

data class Category(
    val categoryId: Long,
    val categoryName: String
)

fun CategoryDomainModel.asUiModel() = Category(
    categoryId = categoryId,
    categoryName = categoryName
)