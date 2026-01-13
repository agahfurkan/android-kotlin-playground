package com.agah.furkan.data.cart.di

import com.agah.furkan.data.cart.CartRepositoryImpl
import com.agah.furkan.domain.cart.CartRepository
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

