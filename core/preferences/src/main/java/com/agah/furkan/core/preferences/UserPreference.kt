package com.agah.furkan.core.preferences

interface UserPreference {
    fun getToken(): String?
    fun setToken(token: String)
    fun getUsername(): String
    fun setUsername(username: String)
    fun getUserId(): Long
    fun setUserId(userId: Long)
    fun clearAllData()
}