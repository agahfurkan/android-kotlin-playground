package com.agah.furkan.core.logging

import timber.log.Timber

class TimberTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, "app-log   $tag", message, t)
    }
}
