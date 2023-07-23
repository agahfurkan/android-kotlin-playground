package com.agah.furkan.androidplayground.di

import android.app.Application
import androidx.room.Room
import com.agah.furkan.androidplayground.data.local.AppDatabase
import com.agah.furkan.androidplayground.data.local.dao.DummyDao
import com.agah.furkan.product_list.ProductListPagingSource
import com.agah.furkan.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideProductListPagingSource(productRepository: ProductRepository): com.agah.furkan.product_list.ProductListPagingSource {
        return com.agah.furkan.product_list.ProductListPagingSource(productRepository)
    }

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
