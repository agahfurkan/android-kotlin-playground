package com.agah.furkan.domain.announcement

import com.agah.furkan.core.domain.model.DomainResult

interface AnnouncementRepository {
    suspend fun getAnnouncements(): DomainResult<List<Announcement>>
}

