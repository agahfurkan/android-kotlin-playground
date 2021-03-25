package com.agah.furkan.androidplayground.util

import androidx.recyclerview.widget.DiffUtil

class GenericDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }
}
