package com.agah.furkan.androidplayground.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.PokemonRepository
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.response.PokemonResponse
import com.agah.furkan.androidplayground.testing.OpenForTesting
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
class MainScreenVM @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    val pokemonList = pokemonRepository.getPersistedPokemonList()
    var pokemonListTotalCount: Int? = null
    var isRequestActive = false

    init {
        getPokemonDataFromNetwork(offset = 0)
    }

    fun getPokemonDataFromNetwork(offset: Int) {
        viewModelScope.launch {
            isRequestActive = true
            val data = pokemonRepository.getPokemonList(offset, 50)
            if (data is ApiSuccessResponse) {
                pokemonListTotalCount = data.data.count
                cachePokemonData(data.data)
            }
            isRequestActive = false
        }
    }

    private fun cachePokemonData(pokemonResponse: PokemonResponse) {
        viewModelScope.launch {
            pokemonRepository.cachePokemonData(pokemonResponse)
        }
    }
}