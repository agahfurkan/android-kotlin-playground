package com.agah.furkan.androidplayground.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.PokemonRepository
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.response.PokemonResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenVM @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    val pokemonList = pokemonRepository.getPersistedPokemonList()

    init {
        viewModelScope.launch {
            val data = pokemonRepository.getPokemonList(0, 50)
            if (data is ApiSuccessResponse) {
                cachePokemonData(data.data)
            }
        }
    }

    private fun cachePokemonData(pokemonResponse: PokemonResponse) {
        viewModelScope.launch {
            pokemonRepository.cachePokemonData(pokemonResponse)
        }
    }
}