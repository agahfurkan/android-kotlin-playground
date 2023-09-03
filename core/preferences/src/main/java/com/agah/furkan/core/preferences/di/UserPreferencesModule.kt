package com.agah.furkan.core.preferences.di

import android.content.Context
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.core.preferences.UserPreferenceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserPreferencesModule {

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreference {
        return UserPreferenceImpl(context)
    }

}