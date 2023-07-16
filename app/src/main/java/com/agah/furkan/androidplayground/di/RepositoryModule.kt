package com.agah.furkan.androidplayground.di

import com.agah.furkan.androidplayground.data.repository.CategoryRepositoryImpl
import com.agah.furkan.androidplayground.data.repository.ProductRepositoryImpl
import com.agah.furkan.androidplayground.data.repository.fake.FakeAnnouncementRepository
import com.agah.furkan.androidplayground.data.repository.fake.FakeProductDetailRepository
import com.agah.furkan.androidplayground.domain.repository.AnnouncementRepository
import com.agah.furkan.androidplayground.domain.repository.CategoryRepository
import com.agah.furkan.androidplayground.domain.repository.ProductDetailRepository
import com.agah.furkan.androidplayground.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    abstract fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun provideAnnouncementRepository(fakeAnnouncementRepository: FakeAnnouncementRepository): AnnouncementRepository

    @Binds
    abstract fun provideProductDetailRepository(fakeProductDetailRepository: FakeProductDetailRepository): ProductDetailRepository
}

