package com.agah.furkan.androidplayground.util

import androidx.recyclerview.widget.DiffUtil
import com.agah.furkan.androidplayground.base.ListModel

class GenericDiffUtil<T : ListModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}
