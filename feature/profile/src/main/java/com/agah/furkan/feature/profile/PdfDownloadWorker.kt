package com.agah.furkan.feature.profile

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.agah.furkan.core.notification.NotificationId
import com.agah.furkan.core.notification.NotificationManager
import com.agah.furkan.data.pdf.PdfRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PdfDownloadWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val pdfRepository: PdfRepository,
    private val notificationManager: NotificationManager
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        notificationManager.showProgressNotification(
            notificationId = NotificationId.PROGRESS.id,
            title = "Downloading pdf..."
        )

        val result = pdfRepository.getPdfList()

        notificationManager.cancelNotification(NotificationId.PROGRESS)

        return when (result) {
            is com.agah.furkan.core.data.model.Result.Success -> Result.success()
            is com.agah.furkan.core.data.model.Result.Failure -> Result.failure()
        }
    }
}