package com.agah.furkan.data.cart.di

import com.agah.furkan.data.cart.remote.CartService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartServiceModule {
    @Singleton
    @Provides
    fun provideCartService(retrofit: Retrofit): CartService =
        retrofit.create(CartService::class.java)
}