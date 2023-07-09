package com.agah.furkan.androidplayground.data.repository.fake

import com.agah.furkan.androidplayground.data.model.Announcement
import com.agah.furkan.androidplayground.domain.repository.AnnouncementRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeAnnouncementRepository @Inject constructor() : AnnouncementRepository {
    override fun getAnnouncements(): Flow<List<Announcement>> {
        return flow {
            delay(5000)
            emit(
                listOf(
                    Announcement(description = "persecuti"),
                    Announcement(description = "maiorum"),
                    Announcement(description = "inceptos"),
                    Announcement(description = "harum"),
                    Announcement(description = "tritani"),
                    Announcement(description = "ancillae"),
                    Announcement(description = "eleifend"),
                    Announcement(description = "tantas"),
                    Announcement(description = "autem"),
                    Announcement(description = "habeo"),
                )
            )
        }
    }
}