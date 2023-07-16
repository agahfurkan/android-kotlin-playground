package com.agah.furkan.androidplayground.di

import android.app.Application
import androidx.room.Room
import com.agah.furkan.androidplayground.data.local.AppDatabase
import com.agah.furkan.androidplayground.data.local.dao.DummyDao
import com.agah.furkan.androidplayground.data.remote.service.CategoryService
import com.agah.furkan.androidplayground.data.remote.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCategoryService(retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

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
