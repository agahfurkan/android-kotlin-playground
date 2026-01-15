package com.agah.furkan.data.user.di

import com.agah.furkan.data.user.UserRepositoryImpl
import com.agah.furkan.data.user.kmp.StaticUserRepository
import com.agah.furkan.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        @Named("flavor") flavor: String,
        staticUserRepository: StaticUserRepository,
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository {
        return if (flavor == "demo") {
            staticUserRepository
        } else {
            userRepositoryImpl
        }
    }
}

