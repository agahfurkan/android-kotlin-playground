package com.agah.furkan.data.di

import com.agah.furkan.data.announcement.AnnouncementRepository
import com.agah.furkan.data.announcement.AnnouncementRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AnnouncementRepositoryModule {

    @Binds
    abstract fun provideAnnouncementRepository(announcementRepository: AnnouncementRepositoryImpl): AnnouncementRepository
}

