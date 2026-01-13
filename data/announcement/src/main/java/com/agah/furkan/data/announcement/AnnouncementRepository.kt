package com.agah.furkan.data.announcement

import com.agah.furkan.core.domain.model.DomainResult

interface AnnouncementRepository {
    suspend fun getAnnouncements(): DomainResult<List<Announcement>>
}