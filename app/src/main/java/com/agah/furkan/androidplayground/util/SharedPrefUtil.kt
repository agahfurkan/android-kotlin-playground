package com.agah.furkan.androidplayground.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.agah.furkan.androidplayground.R

object SharedPrefUtil {
    private var sharedPref: SharedPreferences? = null
    private const val TOKEN_KEY = "userToken"
    private const val USERNAME_KEY = "username"
    private const val USERID_KEY = "userid"

    fun init(application: Application) {
        sharedPref = application.getSharedPreferences(
            application.getString(
                R.string.app_name
            ),
            Context.MODE_PRIVATE
        )
    }

    fun setToken(token: String) {
        sharedPref?.edit()?.putString(TOKEN_KEY, token)?.apply()
    }

    fun getToken(): String? = sharedPref?.getString(TOKEN_KEY, null)

    fun setUsername(username: String) {
        sharedPref?.edit()?.putString(USERNAME_KEY, username)?.apply()
    }

    fun getUsername(): String = sharedPref?.getString(USERNAME_KEY, null) ?: ""

    fun setUserid(userid: Long) {
        sharedPref?.edit()?.putLong(USERID_KEY, userid)?.apply()
    }

    fun getUserId(): Long = sharedPref?.getLong(USERID_KEY, 0L) ?: 0L

    fun clearAllData() {
        sharedPref?.edit()?.clear()?.apply()
    }
}
