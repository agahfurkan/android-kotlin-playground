package com.agah.furkan.androidplayground.di

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


}
