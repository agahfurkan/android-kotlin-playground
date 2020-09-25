package com.agah.furkan.androidplayground.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonCache(@PrimaryKey val name: String = "", val url: String = "")
