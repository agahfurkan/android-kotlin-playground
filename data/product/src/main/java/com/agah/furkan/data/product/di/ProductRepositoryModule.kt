package com.agah.furkan.data.product.di

import com.agah.furkan.data.product.FakeProductDetailRepository
import com.agah.furkan.data.product.ProductDetailRepository
import com.agah.furkan.data.product.ProductRepository
import com.agah.furkan.data.product.ProductRepositoryImpl
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
    abstract fun provideProductDetailRepository(fakeProductDetailRepository: FakeProductDetailRepository): ProductDetailRepository

}

