package com.agah.furkan.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserPreferenceImpl @Inject constructor(private val applicationContext: Context) :
    UserPreference {

    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer
    )

    private val store = applicationContext.userPreferencesStore

    override fun getTokenSync(): String? {
        return runBlocking {
            store.data.firstOrNull()?.token
        }
    }

    override suspend fun getToken(): String? {
        return store.data.firstOrNull()?.token
    }

    override suspend fun setToken(token: String) {
        store.updateData { preferences ->
            preferences.toBuilder().setToken(token).build()
        }
    }

    override suspend fun getUsername(): String {
        return store.data.firstOrNull()?.username.orEmpty()
    }

    override suspend fun setUsername(username: String) {
        store.updateData { preferences ->
            preferences.toBuilder().setUsername(username).build()
        }
    }

    override suspend fun getUserId(): Long {
        return store.data.firstOrNull()?.userId ?: 0L
    }

    override suspend fun setUserId(userId: Long) {
        store.updateData { preferences ->
            preferences.toBuilder().setUserId(userId).build()
        }
    }

    override suspend fun clearAllData() {
        store.updateData { preferences ->
            preferences.toBuilder().clear().build()
        }
    }

    companion object {
        private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
    }
}
