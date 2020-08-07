package com.agah.furkan.androidplayground.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.data.local.model.PokemonCache
import com.agah.furkan.androidplayground.databinding.ItemviewMainScreenPokemonListBinding


class MainScreenPokemonAdapter :
    ListAdapter<PokemonCache, MainScreenPokemonAdapter.PokemonViewHolder>(object :
        DiffUtil.ItemCallback<PokemonCache>() {
        override fun areItemsTheSame(oldItem: PokemonCache, newItem: PokemonCache): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokemonCache, newItem: PokemonCache): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder.from(parent)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PokemonViewHolder(private val binding: ItemviewMainScreenPokemonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonCache) {
            binding.pokemonName.text = pokemon.name
        }

        companion object {
            fun from(parent: ViewGroup): PokemonViewHolder {
                val binding = ItemviewMainScreenPokemonListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return PokemonViewHolder(binding)
            }
        }
    }
}