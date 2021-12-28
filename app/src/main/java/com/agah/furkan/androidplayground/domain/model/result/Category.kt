package com.agah.furkan.androidplayground.domain.model.result

import com.agah.furkan.androidplayground.base.ListModel

data class Category(
    val categoryId: Long,
    val categoryName: String
) : ListModel {
    override val id: Long
        get() = categoryId
}
