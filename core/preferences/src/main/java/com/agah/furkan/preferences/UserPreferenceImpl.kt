package com.agah.furkan.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class UserPreferenceImpl @Inject constructor(applicationContext: Context) : UserPreference {
    private var sharedPref: SharedPreferences? = null

    init {
        sharedPref = applicationContext.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun getToken(): String? {
        return sharedPref?.getString(KEY_TOKEN, null)
    }

    override fun setToken(token: String) {
        sharedPref?.edit()?.putString(KEY_TOKEN, token)?.apply()
    }

    override fun getUsername(): String {
        return sharedPref?.getString(KEY_USERNAME, null) ?: ""
    }

    override fun setUsername(username: String) {
        sharedPref?.edit()?.putString(KEY_USERNAME, username)?.apply()
    }

    override fun getUserId(): Long {
        return sharedPref?.getLong(KEY_USER_ID, 0L) ?: 0L
    }

    override fun setUserId(userId: Long) {
        sharedPref?.edit()?.putLong(KEY_USER_ID, userId)?.apply()
    }

    override fun clearAllData() {
        sharedPref?.edit()?.clear()?.apply()
    }

    companion object {
        private const val PREF_NAME = "userPref"
        private const val KEY_TOKEN = "userToken"
        private const val KEY_USERNAME = "username"
        private const val KEY_USER_ID = "userid"
    }
}