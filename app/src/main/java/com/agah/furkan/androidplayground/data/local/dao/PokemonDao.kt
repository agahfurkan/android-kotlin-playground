package com.agah.furkan.androidplayground.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agah.furkan.androidplayground.data.local.model.PokemonCache

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonCache: PokemonCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonCacheList: List<PokemonCache>)

    @Query("SELECT * FROM pokemon")
    fun getPokemonList(): LiveData<List<PokemonCache>>
}
