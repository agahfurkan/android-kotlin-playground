package com.agah.furkan.core.view.recyclerview

interface BaseListModel {
    val id: Int

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
