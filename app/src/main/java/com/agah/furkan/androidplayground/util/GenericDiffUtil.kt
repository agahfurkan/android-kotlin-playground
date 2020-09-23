package com.agah.furkan.androidplayground.util

import androidx.recyclerview.widget.DiffUtil
import com.agah.furkan.androidplayground.data.local.model.PokemonCache
import com.agah.furkan.androidplayground.data.web.model.response.PokemonDetailResponse

class GenericDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is PokemonCache -> oldItem as PokemonCache == newItem
            is PokemonDetailResponse.Ability -> oldItem as PokemonDetailResponse.Ability == newItem
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when (newItem) {
            is PokemonCache -> oldItem as PokemonCache == newItem
            is PokemonDetailResponse.Ability -> oldItem as PokemonDetailResponse.Ability == newItem
            else -> false
        }
    }
}