package com.agah.furkan.data.announcement.remote.response

import com.agah.furkan.core.data.model.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnnouncementResponse(
    @Json(name = "announcementList")
    val announcementList: List<Announcement>
) : BaseResponse() {
    @JsonClass(generateAdapter = true)
    data class Announcement(
        @Json(name = "description")
        val description: String
    )
}