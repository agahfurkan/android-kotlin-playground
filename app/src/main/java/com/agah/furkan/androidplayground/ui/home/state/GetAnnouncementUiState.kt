package com.agah.furkan.androidplayground.ui.home.state

import com.agah.furkan.androidplayground.data.model.Announcement

sealed class GetAnnouncementUiState {
    data class Success(val categoryList: List<Announcement>) : GetAnnouncementUiState()
    object Loading : GetAnnouncementUiState()
    data class Failure(val failureMessage: String) : GetAnnouncementUiState()
}