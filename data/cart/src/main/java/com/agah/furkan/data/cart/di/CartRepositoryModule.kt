package com.agah.furkan.data.cart.di

import com.agah.furkan.data.cart.CartRepositoryImpl
import com.agah.furkan.data.cart.kmp.StaticCartRepository
import com.agah.furkan.domain.cart.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartRepositoryModule {
    @Provides
    @Singleton
    fun provideCartRepository(
        @Named("flavor") flavor: String,
        staticCartRepository: StaticCartRepository,
        cartRepository: CartRepositoryImpl
    ): CartRepository {
        return if (flavor == "demo") {
            staticCartRepository
        } else {
            cartRepository
        }
    }
}

