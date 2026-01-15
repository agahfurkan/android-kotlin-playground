package com.agah.furkan.data.category.di

import com.agah.furkan.data.category.CategoryRepositoryImpl
import com.agah.furkan.data.category.kmp.StaticCategoryRepository
import com.agah.furkan.domain.category.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryRepositoryModule {
    @Provides
    @Singleton
    fun provideCategoryRepository(
        @Named("flavor") flavor: String,
        staticCategoryRepository: StaticCategoryRepository,
        categoryRepository: CategoryRepositoryImpl
    ): CategoryRepository {
        return if (flavor == "demo") {
            staticCategoryRepository
        } else {
            categoryRepository
        }
    }
}

