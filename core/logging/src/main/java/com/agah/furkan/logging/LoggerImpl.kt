package com.agah.furkan.logging

import timber.log.Timber

class LoggerImpl : Logger {
    override fun i(message: String) {
        Timber.i(message)
    }

    override fun plant() {
        Timber.plant(TimberTree())
    }
}