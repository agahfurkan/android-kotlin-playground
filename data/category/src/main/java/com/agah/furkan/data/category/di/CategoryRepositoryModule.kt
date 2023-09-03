package com.agah.furkan.data.category.di

import com.agah.furkan.data.category.CategoryRepository
import com.agah.furkan.data.category.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryRepositoryModule {

    @Binds
    abstract fun provideCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository
}

