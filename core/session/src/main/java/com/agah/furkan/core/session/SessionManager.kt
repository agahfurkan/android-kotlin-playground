package com.agah.furkan.core.session

import com.agah.furkan.core.preferences.UserPreference
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(private val userPreference: UserPreference) {
    private var sessionListenerList = arrayListOf<SessionListener>()

    fun addSessionListener(sessionListener: SessionListener) {
        sessionListenerList.add(sessionListener)
    }

    fun onUnauthorizedResponseReceived() {
        MainScope().launch {
            userPreference.clearAllData()
        }
        sessionListenerList.forEach { it.sessionEnded() }
    }

    fun onLoginSuccess() {
        sessionListenerList.forEach { it.sessionStarted() }
    }
}