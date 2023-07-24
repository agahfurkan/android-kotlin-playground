package com.agah.furkan.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DummyEntity")
data class DummyEntity(@PrimaryKey val id: Int)
