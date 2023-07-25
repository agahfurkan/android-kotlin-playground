package com.agah.furkan.domain.product.di

import com.agah.furkan.domain.product.ProductListPagingSource
import com.agah.furkan.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {
    @Singleton
    @Provides
    fun provideProductListPagingSource(productRepository: ProductRepository): ProductListPagingSource {
        return ProductListPagingSource(productRepository)
    }
}
