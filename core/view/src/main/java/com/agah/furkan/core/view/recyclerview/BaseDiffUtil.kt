package com.agah.furkan.core.view.recyclerview

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtil<T : BaseListModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }
}