package com.agah.furkan.androidplayground.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.data.local.model.PokemonCache
import com.agah.furkan.androidplayground.databinding.ItemviewMainScreenPokemonListBinding
import com.agah.furkan.androidplayground.util.GenericDiffUtil
import com.agah.furkan.androidplayground.util.ItemClickListener

class MainScreenPokemonAdapter(private val itemClickListener: ItemClickListener<PokemonCache>) :
    ListAdapter<PokemonCache, MainScreenPokemonAdapter.PokemonViewHolder>(GenericDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder.from(parent)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class PokemonViewHolder(private val binding: ItemviewMainScreenPokemonListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonCache, itemClickListener: ItemClickListener<PokemonCache>) {
            binding.pokemonName.text = pokemon.name
            binding.root.setOnClickListener {
                itemClickListener.onItemClicked(pokemon)
            }
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
