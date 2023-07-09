package com.agah.furkan.androidplayground.di

import com.agah.furkan.androidplayground.data.mapper.ErrorMapperImpl
import com.agah.furkan.androidplayground.data.repository.CartRepositoryImpl
import com.agah.furkan.androidplayground.data.repository.CategoryRepositoryImpl
import com.agah.furkan.androidplayground.data.repository.ProductRepositoryImpl
import com.agah.furkan.androidplayground.data.repository.UserRepositoryImpl
import com.agah.furkan.androidplayground.data.repository.fake.FakeAnnouncementRepository
import com.agah.furkan.androidplayground.data.repository.fake.FakeProductDetailRepository
import com.agah.furkan.androidplayground.domain.ErrorMapper
import com.agah.furkan.androidplayground.domain.repository.AnnouncementRepository
import com.agah.furkan.androidplayground.domain.repository.CartRepository
import com.agah.furkan.androidplayground.domain.repository.CategoryRepository
import com.agah.furkan.androidplayground.domain.repository.ProductDetailRepository
import com.agah.furkan.androidplayground.domain.repository.ProductRepository
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideErrorMapper(errorMapper: ErrorMapperImpl): ErrorMapper

    @Binds
    abstract fun provideCartRepository(cartRepository: CartRepositoryImpl): CartRepository

    @Binds
    abstract fun provideCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    abstract fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun provideAnnouncementRepository(fakeAnnouncementRepository: FakeAnnouncementRepository): AnnouncementRepository

    @Binds
    abstract fun provideProductDetailRepository(fakeProductDetailRepository: FakeProductDetailRepository): ProductDetailRepository
}

