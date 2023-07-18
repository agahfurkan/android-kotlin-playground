package com.agah.furkan.data.announcement

import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {
    fun getAnnouncements(): Flow<List<Announcement>>
}