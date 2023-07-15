package com.agah.furkan.cart.di

import com.agah.furkan.cart.CartRepository
import com.agah.furkan.cart.CartRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CartRepositoryModule {

    @Binds
    abstract fun provideCartRepository(cartRepository: CartRepositoryImpl): CartRepository
}

