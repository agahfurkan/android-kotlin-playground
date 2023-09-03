package com.agah.furkan.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agah.furkan.core.database.dao.DummyDao
import com.agah.furkan.core.database.model.DummyEntity

@Database(entities = [DummyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
}
