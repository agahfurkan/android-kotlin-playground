package com.agah.furkan.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.data.announcement.AnnouncementRepository
import com.agah.furkan.home.state.GetAnnouncementUiState
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
            val result=announcementRepository.getAnnouncements()
            when(result){
                is com.agah.furkan.data.model.Result.Failure -> TODO()
                is com.agah.furkan.data.model.Result.Success -> TODO()
            }
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