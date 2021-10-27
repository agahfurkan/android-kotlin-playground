package com.agah.furkan.androidplayground.data.domain.model

import com.agah.furkan.androidplayground.base.ListModel

data class Category(
    val categoryId: Long,
    val categoryName: String
) : ListModel {
    override val id: Long
        get() = categoryId
}
