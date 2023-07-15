package com.agah.furkan.androidplayground.ui.userprofile

import androidx.lifecycle.ViewModel
import com.agah.furkan.preferences.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val userPreference: UserPreference) :
    ViewModel() {

    fun logout() {
        userPreference.clearAllData()
    }
}