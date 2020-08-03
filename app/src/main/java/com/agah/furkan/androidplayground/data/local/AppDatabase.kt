package com.agah.furkan.androidplayground.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agah.furkan.androidplayground.data.local.dao.PokemonDao
import com.agah.furkan.androidplayground.data.local.model.PokemonCache

@Database(entities = [PokemonCache::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}