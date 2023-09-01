package com.agah.furkan.core.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

@SuppressLint("MissingPermission")
class NotificationManagerImpl(private val context: Context) : NotificationManager {
    override fun createNotificationChannel(
        channelId: String,
        channelName: String,
        channelDescription: String,
        importance: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                importance
            ).apply {
                description = channelDescription
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("InlinedApi")
    override fun showDefaultNotification(
        intent: Intent,
        notificationId: Int,
        title: String,
        content: String
    ) {
        val channelId = "123"
        val requestCode = 0

        createNotificationChannel(
            channelId = channelId,
            channelName = "Default Notifications",
            channelDescription = "Default Notifications",
            importance = android.app.NotificationManager.IMPORTANCE_DEFAULT
        )

        val pendingIntent = PendingIntent.getActivity(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_bell_outline)
            .setContentTitle(title)
            .setContentText(content)
            .setSound(defaultSoundUri)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(notificationId, notificationBuilder.build())
    }


    @SuppressLint("InlinedApi")
    override fun showProgressNotification(
        notificationId: Int,
        title: String,
        content: String?,
        maxProgress: Int,
        progress: Int,
        indeterminate: Boolean
    ) {
        val chanelId = "12345"

        createNotificationChannel(
            channelId = chanelId,
            channelName = "Download Notifications",
            channelDescription = "Download Notifications",
            importance = android.app.NotificationManager.IMPORTANCE_HIGH
        )

        val builder = NotificationCompat.Builder(context, chanelId)
            .setSmallIcon(R.drawable.baseline_cloud_download_24)
            .setContentTitle(title)
            .setOngoing(true)
            .setProgress(maxProgress, progress, indeterminate).apply {
                if (!content.isNullOrEmpty()) {
                    setContentText(content)
                }
            }

        NotificationManagerCompat.from(context).notify(notificationId, builder.build())
    }

    override fun cancelNotification(notificationId: Int) {
        NotificationManagerCompat.from(context).cancel(notificationId)
    }
}