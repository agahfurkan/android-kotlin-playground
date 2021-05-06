package com.agah.furkan.androidplayground.util

import androidx.recyclerview.widget.DiffUtil
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse

class GenericDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is CategoryResponse.Category -> oldItem as CategoryResponse.Category == newItem
            is ProductResponse.Product -> oldItem as ProductResponse.Product == newItem
            is CartResponse.Cart -> oldItem as CartResponse.Cart == newItem
            else -> throw RuntimeException("err!")
        }
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is CategoryResponse.Category -> oldItem as CategoryResponse.Category == newItem
            is ProductResponse.Product -> oldItem as ProductResponse.Product == newItem
            is CartResponse.Cart -> oldItem as CartResponse.Cart == newItem
            else -> throw RuntimeException("err!")
        }
    }
}
