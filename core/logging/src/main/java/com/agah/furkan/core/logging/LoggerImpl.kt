package com.agah.furkan.core.logging

import timber.log.Timber

class LoggerImpl : Logger {
    override fun i(message: String) {
        Timber.i(message)
    }

    override fun plant() {
        Timber.plant(TimberTree())
    }
}