package com.agah.furkan.androidplayground.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashFragmentVM @Inject constructor() : ViewModel() {
    private val _navigateEvent = MutableLiveData<Boolean>()
    val navigateEvent: LiveData<Boolean> get() = _navigateEvent

    init {
        viewModelScope.launch {
            delay(3000)
            _navigateEvent.postValue(true)
        }
    }
}