package com.agah.furkan.feature.profile

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.agah.furkan.data.pdf.PdfRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PdfDownloadWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val pdfRepository: PdfRepository
) : CoroutineWorker(appContext, workerParams) {

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = "channel name"
            val description = "description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("1234", name, importance)
            channel.description = description

            val notificationManager =
                appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            notificationManager?.createNotificationChannel(channel)

        }

        val builder = NotificationCompat.Builder(appContext, "1234")
            .setSmallIcon(R.drawable.baseline_cloud_download_24)
            .setContentTitle("Downloading pdf...")
            .setOngoing(true)
            .setProgress(0, 0, true)

        NotificationManagerCompat.from(appContext).notify(123, builder.build())
        val result = pdfRepository.getPdfList()

        NotificationManagerCompat.from(appContext).cancel(123)
        return when (result) {
            is com.agah.furkan.core.data.model.Result.Success -> Result.success()
            is com.agah.furkan.core.data.model.Result.Failure -> Result.failure()
        }

    }
}