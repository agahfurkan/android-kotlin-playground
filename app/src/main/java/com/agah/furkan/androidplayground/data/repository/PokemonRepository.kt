package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.local.dao.PokemonDao
import com.agah.furkan.androidplayground.data.local.model.PokemonCache
import com.agah.furkan.androidplayground.data.web.model.response.PokemonResponse
import com.agah.furkan.androidplayground.data.web.service.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonDao: PokemonDao
) {
    suspend fun getPokemonList(offset: Int, limit: Int) = withContext(Dispatchers.IO) {
        pokemonService.getPokemonList(offset, limit)
    }

    suspend fun cachePokemonData(pokemonResponse: PokemonResponse) = withContext(Dispatchers.IO) {
        if (!pokemonResponse.results.isNullOrEmpty()) {
            val data = ArrayList<PokemonCache>()
            pokemonResponse.results.forEach { pokemon ->
                data.add(
                    PokemonCache(
                        name = pokemon?.name.toString(),
                        url = pokemon?.url.toString()
                    )
                )
            }
            pokemonDao.insertPokemonList(data)
        }
    }

    fun getPersistedPokemonList() = pokemonDao.getPokemonList()
}