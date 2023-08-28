package com.agah.furkan.core.logging.di

import com.agah.furkan.core.logging.Logger
import com.agah.furkan.core.logging.LoggerImpl
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