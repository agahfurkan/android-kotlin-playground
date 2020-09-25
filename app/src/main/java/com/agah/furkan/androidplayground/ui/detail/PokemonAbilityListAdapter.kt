package com.agah.furkan.androidplayground.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.data.web.model.response.PokemonDetailResponse
import com.agah.furkan.androidplayground.databinding.ItemviewPokemonAbilityListBinding
import com.agah.furkan.androidplayground.util.GenericDiffUtil

class PokemonAbilityListAdapter :
    ListAdapter<PokemonDetailResponse.Ability, PokemonAbilityListAdapter.ViewHolder>(GenericDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemviewPokemonAbilityListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ability: PokemonDetailResponse.Ability) {
            binding.abilityName.text = ability.ability.name
            binding.abilityIsHidden.isChecked = ability.isHidden
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemviewPokemonAbilityListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }
}
