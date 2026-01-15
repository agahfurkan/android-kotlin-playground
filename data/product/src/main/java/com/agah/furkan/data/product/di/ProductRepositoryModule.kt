package com.agah.furkan.data.product.di

import com.agah.furkan.data.product.FakeProductDetailRepository
import com.agah.furkan.data.product.ProductRepositoryImpl
import com.agah.furkan.data.product.kmp.StaticProductRepository
import com.agah.furkan.domain.product.ProductDetailRepository
import com.agah.furkan.domain.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductRepositoryModule {
    @Provides
    @Singleton
    fun provideProductDetailRepository(
        @Named("flavor") flavor: String,
        fakeProductDetailRepository: FakeProductDetailRepository
    ): ProductDetailRepository {
        return fakeProductDetailRepository
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        @Named("flavor") flavor: String,
        staticProductRepository: StaticProductRepository,
        productRepository: ProductRepositoryImpl
    ): ProductRepository {
        return if (flavor == "demo") {
            staticProductRepository
        } else {
            productRepository
        }
    }
}

