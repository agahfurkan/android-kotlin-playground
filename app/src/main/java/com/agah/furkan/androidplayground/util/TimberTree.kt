package com.agah.furkan.androidplayground.util

import timber.log.Timber

class TimberTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, "app-log   $tag", message, t)
    }
}
