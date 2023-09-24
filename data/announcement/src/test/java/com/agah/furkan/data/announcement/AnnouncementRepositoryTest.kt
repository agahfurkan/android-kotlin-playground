package com.agah.furkan.data.announcement

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.announcement.remote.response.AnnouncementResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AnnouncementRepositoryTest {
    private lateinit var announcementRepository: AnnouncementRepository

    private val announcementService = mockk<AnnouncementService>()
    private val errorMapper = mockk<ErrorMapper>()

    @Before
    fun setUp() {
        announcementRepository = AnnouncementRepositoryImpl(announcementService, errorMapper)
    }

    @Test
    fun `getAnnouncements if response success returns Success with data`() = runBlocking {
        coEvery {
            announcementService.getAnnouncements()
        } returns AnnouncementResponse(
            listOf(
                AnnouncementResponse.Announcement(description = "persecuti"),
                AnnouncementResponse.Announcement(description = "maiorum"),
                AnnouncementResponse.Announcement(description = "inceptos"),
                AnnouncementResponse.Announcement(description = "harum"),
                AnnouncementResponse.Announcement(description = "tritani"),
                AnnouncementResponse.Announcement(description = "ancillae"),
                AnnouncementResponse.Announcement(description = "eleifend"),
                AnnouncementResponse.Announcement(description = "tantas"),
                AnnouncementResponse.Announcement(description = "autem"),
                AnnouncementResponse.Announcement(description = "habeo"),
            )
        )

        val announcements = announcementRepository.getAnnouncements()
        assert(announcements is Result.Success)
        assert((announcements as Result.Success).data.isNotEmpty())
    }

    @Test
    fun `getAnnouncements if response error returns Failure`() = runBlocking {
        coEvery {
            announcementService.getAnnouncements()
        } returns AnnouncementResponse(emptyList()).apply {
            isSuccess = false
        }

        val announcements = announcementRepository.getAnnouncements()

        assert(announcements is Result.Success)
    }

}