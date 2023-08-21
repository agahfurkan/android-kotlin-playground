package com.agah.furkan.core.session

import com.agah.furkan.preferences.UserPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(private val userPreference: UserPreference) {
    private var sessionListenerList = arrayListOf<SessionListener>()

    fun addSessionListener(sessionListener: SessionListener) {
        sessionListenerList.add(sessionListener)
    }

    fun onUnauthorizedResponseReceived() {
        userPreference.clearAllData()
        sessionListenerList.forEach { it.sessionEnded() }
    }

    fun onLoginSuccess() {
        sessionListenerList.forEach { it.sessionStarted() }
    }
}