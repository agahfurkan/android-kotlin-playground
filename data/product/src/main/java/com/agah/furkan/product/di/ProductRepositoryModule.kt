package com.agah.furkan.product.di

import com.agah.furkan.product.ProductRepository
import com.agah.furkan.product.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductRepositoryModule {

    @Binds
    abstract fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun provideProductDetailRepository(fakeProductDetailRepository: com.agah.furkan.product.FakeProductDetailRepository): com.agah.furkan.product.ProductDetailRepository

}

