package com.agah.furkan.androidplayground.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agah.furkan.androidplayground.data.local.dao.DummyDao
import com.agah.furkan.androidplayground.data.local.model.DummyEntity

@Database(entities = [DummyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
}
