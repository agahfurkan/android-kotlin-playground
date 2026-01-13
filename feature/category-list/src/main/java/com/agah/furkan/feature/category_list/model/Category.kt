package com.agah.furkan.feature.category_list.model

import com.agah.furkan.domain.category.Category as DomainCategory

data class Category(
    val categoryId: Long,
    val categoryName: String
)

fun DomainCategory.asUiModel() = Category(
    categoryId = categoryId,
    categoryName = categoryName
)