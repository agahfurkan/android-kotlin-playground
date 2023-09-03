package com.agah.furkan.feature.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.agah.furkan.core.preferences.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProfileScreenViewModel @Inject constructor(
    private val userPreference: UserPreference,
    private val application: Application
) : ViewModel() {

    fun logout() {
        userPreference.clearAllData()
    }

    fun downloadPdf() {
        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<PdfDownloadWorker>()
                .build()
        WorkManager
            .getInstance(application)
            .enqueue(uploadWorkRequest)
    }
}