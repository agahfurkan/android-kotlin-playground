package com.agah.furkan.feature.home.state

import com.agah.furkan.data.announcement.Announcement

internal sealed class GetAnnouncementUiState {
    data class Success(val categoryList: List<Announcement>) : GetAnnouncementUiState()
    object Loading : GetAnnouncementUiState()
    data class Failure(val failureMessage: String) : GetAnnouncementUiState()
}