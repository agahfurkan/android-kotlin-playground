package com.agah.furkan.core.notification

import android.content.Intent

interface NotificationManager {
    fun createNotificationChannel(
        channelId: String,
        channelName: String,
        channelDescription: String,
        importance: Int
    )


    fun showDefaultNotification(
        intent: Intent,
        notificationId: Int,
        title: String,
        content: String
    )

    fun showProgressNotification(
        notificationId: Int,
        title: String,
        content: String? = null,
        maxProgress: Int = 0,
        progress: Int = 0,
        indeterminate: Boolean = true
    )

    fun cancelNotification(notificationId: Int)
}