package com.agah.furkan.core.preferences

interface UserPreference {
    fun getTokenSync(): String?
    suspend fun getToken(): String?
    suspend fun setToken(token: String)
    suspend fun getUsername(): String
    suspend fun setUsername(username: String)
    suspend fun getUserId(): Long
    suspend fun setUserId(userId: Long)
    suspend fun clearAllData()
}