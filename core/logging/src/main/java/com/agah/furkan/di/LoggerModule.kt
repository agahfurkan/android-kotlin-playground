package com.agah.furkan.di

import com.agah.furkan.logging.Logger
import com.agah.furkan.logging.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {
    @Singleton
    @Provides
    fun provideLogger(): Logger {
        return LoggerImpl()
    }
}