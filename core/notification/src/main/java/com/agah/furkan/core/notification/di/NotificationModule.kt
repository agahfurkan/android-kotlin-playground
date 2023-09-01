package com.agah.furkan.core.notification.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Singleton
    @Provides
    fun provideNotificationManager(application: Application): com.agah.furkan.core.notification.NotificationManager {
        return com.agah.furkan.core.notification.NotificationManagerImpl(application)
    }
}