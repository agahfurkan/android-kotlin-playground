package com.agah.furkan.data.announcement

import com.agah.furkan.data.remote.response.AnnouncementResponse
import retrofit2.http.GET

interface AnnouncementService {
    @GET("announcement/getAnnouncements")
    suspend fun getAnnouncements(): AnnouncementResponse
}
