package com.agah.furkan.androidplayground

import android.app.Application
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefUtil.init(this)
    }
}
