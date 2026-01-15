package com.agah.furkan.data.announcement.kmp

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.announcement.Announcement
import com.agah.furkan.domain.announcement.AnnouncementRepository
import com.agah.furkan.playgrounddatamodule.KmpAnnouncement
import com.agah.furkan.playgrounddatamodule.StaticAnnouncementData
import kotlinx.coroutines.delay
import javax.inject.Inject

class StaticAnnouncementRepository @Inject constructor() : AnnouncementRepository {

    override suspend fun getAnnouncements(): DomainResult<List<Announcement>> {
        // Simulate network delay
        delay(350)

        val staticAnnouncements = StaticAnnouncementData.getAnnouncements()

        return DomainResult.Success(
            staticAnnouncements.map { it.toDomainAnnouncement() }
        )
    }

    // Mapper function
    private fun KmpAnnouncement.toDomainAnnouncement() = Announcement(
        description = description
    )
}

