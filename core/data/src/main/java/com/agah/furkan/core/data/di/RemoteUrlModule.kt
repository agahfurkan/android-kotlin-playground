package com.agah.furkan.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteUrlModule {
    @Provides
    @Singleton
    @Named("remoteUrl")
    fun provideRemoteUrl(): String {
        return "https://10.0.2.2:5000/api/"
    }
}