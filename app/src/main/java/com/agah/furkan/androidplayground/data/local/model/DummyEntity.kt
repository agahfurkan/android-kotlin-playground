package com.agah.furkan.androidplayground.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DummyEntity")
data class DummyEntity(@PrimaryKey val id: Int)
