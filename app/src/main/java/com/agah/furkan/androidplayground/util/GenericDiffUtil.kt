package com.agah.furkan.androidplayground.util

import androidx.recyclerview.widget.DiffUtil
import com.agah.furkan.androidplayground.data.local.model.PokemonCache

class GenericDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is PokemonCache -> oldItem as PokemonCache == newItem
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when (newItem) {
            is PokemonCache -> oldItem as PokemonCache == newItem
            else -> false
        }
    }
}