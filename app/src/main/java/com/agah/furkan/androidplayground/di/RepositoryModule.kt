package com.agah.furkan.androidplayground.di

import com.agah.furkan.androidplayground.data.repository.fake.FakeAnnouncementRepository
import com.agah.furkan.androidplayground.domain.repository.AnnouncementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAnnouncementRepository(fakeAnnouncementRepository: FakeAnnouncementRepository): AnnouncementRepository
}

