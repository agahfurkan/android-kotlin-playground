package com.agah.furkan.androidplayground

import android.app.Application
import com.agah.furkan.logging.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        logger.plant()
    }
}
