package com.agah.furkan.data.announcement.di

import com.agah.furkan.data.announcement.AnnouncementRepositoryImpl
import com.agah.furkan.data.announcement.kmp.StaticAnnouncementRepository
import com.agah.furkan.domain.announcement.AnnouncementRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnnouncementRepositoryModule {

    @Provides
    @Singleton
    fun provideAnnouncementRepository(
        @Named("flavor") flavor: String,
        staticAnnouncementRepository: StaticAnnouncementRepository,
        announcementRepository: AnnouncementRepositoryImpl
    ): AnnouncementRepository {
        return if (flavor == "demo") {
            staticAnnouncementRepository
        } else {
            announcementRepository
        }
    }
}

