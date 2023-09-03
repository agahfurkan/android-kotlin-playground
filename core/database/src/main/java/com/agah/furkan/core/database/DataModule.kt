package com.agah.furkan.core.database

import android.app.Application
import androidx.room.Room
import com.agah.furkan.core.database.dao.DummyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "playgroundDB"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDummyDao(appDatabase: AppDatabase): DummyDao = appDatabase.dummyDao()
}