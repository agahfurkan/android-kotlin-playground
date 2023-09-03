package com.agah.furkan.data.pdf

import com.agah.furkan.core.data.model.Result

interface PdfRepository {
    suspend fun getPdfList(): Result<List<PdfModel>>
}