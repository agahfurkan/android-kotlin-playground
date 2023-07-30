package com.agah.furkan.profile

import androidx.lifecycle.ViewModel
import com.agah.furkan.preferences.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProfileScreenViewModel @Inject constructor(private val userPreference: UserPreference) :
    ViewModel() {

    fun logout() {
        userPreference.clearAllData()
    }
}