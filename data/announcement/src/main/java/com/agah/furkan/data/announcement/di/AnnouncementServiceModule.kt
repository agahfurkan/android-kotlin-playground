package com.agah.furkan.data.announcement.di

import com.agah.furkan.data.announcement.AnnouncementService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnnouncementServiceModule {
    @Singleton
    @Provides
    fun provideAnnouncementService(retrofit: Retrofit): AnnouncementService =
        retrofit.create(AnnouncementService::class.java)
}