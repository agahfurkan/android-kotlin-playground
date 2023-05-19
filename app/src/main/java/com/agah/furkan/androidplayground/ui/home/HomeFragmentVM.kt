package com.agah.furkan.androidplayground.ui.home

import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.base.BaseViewModel
import com.agah.furkan.androidplayground.domain.repository.AnnouncementRepository
import com.agah.furkan.androidplayground.ui.home.state.GetAnnouncementUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentVM @Inject constructor(private val announcementRepository: AnnouncementRepository) :
    BaseViewModel() {
    private val _announcementList =
        MutableStateFlow<GetAnnouncementUiState>(GetAnnouncementUiState.Loading)

    init {
        getAnnouncements()
    }

    fun getAnnouncements() {
        viewModelScope.launch {
            announcementRepository.getAnnouncements().collect {
                _announcementList.value = GetAnnouncementUiState.Success(it)
            }
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