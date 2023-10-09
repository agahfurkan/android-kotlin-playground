package com.agah.furkan.androidplayground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FakeRemoteUrlModule {
    @Provides
    @Singleton
    @Named("remoteUrl")
    fun provideRemoteUrl(): String {
        return "http://localhost:5000/api/"
    }
}
