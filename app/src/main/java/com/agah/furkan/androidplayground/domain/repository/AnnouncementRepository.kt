package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.androidplayground.data.model.Announcement
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {
    fun getAnnouncements(): Flow<List<Announcement>>
}