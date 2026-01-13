package com.agah.furkan.data.announcement

import com.agah.furkan.core.data.ErrorMapper
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.data.suspendCall
import com.agah.furkan.domain.announcement.Announcement
import com.agah.furkan.domain.announcement.AnnouncementRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AnnouncementRepositoryImpl(
    private val announcementService: AnnouncementService,
    private val errorMapper: ErrorMapper,
    private val coroutineContext: CoroutineContext
) : AnnouncementRepository {
    @Inject
    constructor(
        announcementService: AnnouncementService,
        errorMapper: ErrorMapper
    ) : this(announcementService, errorMapper, Dispatchers.IO)

    override suspend fun getAnnouncements(): DomainResult<List<Announcement>> {
        return suspendCall(coroutineContext = coroutineContext,
            errorMapper = errorMapper,
            mapOnSuccess = { response -> response }) {
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
        }
    }
}