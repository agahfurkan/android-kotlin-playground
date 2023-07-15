package com.agah.furkan.androidplayground

import android.app.Application
import com.agah.furkan.androidplayground.util.TimberTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberTree())
    }
}
