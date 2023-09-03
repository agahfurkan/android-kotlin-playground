package com.agah.furkan.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.announcement.AnnouncementRepository
import com.agah.furkan.feature.home.state.GetAnnouncementUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(private val announcementRepository: AnnouncementRepository) :
    ViewModel() {
    private val _announcementList =
        MutableStateFlow<GetAnnouncementUiState>(GetAnnouncementUiState.Loading)

    init {
        getAnnouncements()
    }

    fun getAnnouncements() {
        viewModelScope.launch {
            val result = announcementRepository.getAnnouncements()
            
            val state = when (result) {
                is Result.Success -> {
                    GetAnnouncementUiState.Success(result.data)
                }

                is Result.Failure -> {
                    GetAnnouncementUiState.Failure(result.error.errorMessage)
                }
            }
            _announcementList.emit(state)
        }

    }

    fun getExclusiveDeals() {
        //TODO
    }

    fun getRecentlyViewed() {
        //TODO
    }

    fun getBrandBannerList() {
        //TODO
    }
}