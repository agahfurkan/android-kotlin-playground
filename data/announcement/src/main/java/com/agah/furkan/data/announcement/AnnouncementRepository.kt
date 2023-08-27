package com.agah.furkan.data.announcement

import com.agah.furkan.core.data.model.Result

interface AnnouncementRepository {
    suspend fun getAnnouncements(): Result<List<Announcement>>
}