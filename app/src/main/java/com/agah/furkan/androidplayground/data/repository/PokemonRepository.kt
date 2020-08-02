package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.web.service.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(private val pokemonService: PokemonService) {
    suspend fun getPokemonList(offset: Int, limit: Int) = withContext(Dispatchers.IO) {
        pokemonService.getPokemonList(offset, limit)
    }
}