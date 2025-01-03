package com.agah.furkan.androidplayground

import android.content.Intent
import com.agah.furkan.androidplayground.ui.MainActivity
import com.agah.furkan.core.notification.NotificationManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService @Inject constructor() : FirebaseMessagingService() {

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        sendNotification(title.orEmpty(), body.orEmpty())
    }

    override fun onNewToken(token: String) {
        // no-op
    }

    private fun sendNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        notificationManager.showDefaultNotification(intent, 0, title, message)
    }
}
