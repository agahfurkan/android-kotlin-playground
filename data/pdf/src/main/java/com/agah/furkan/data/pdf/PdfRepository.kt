package com.agah.furkan.data.pdf

import com.agah.furkan.core.domain.model.DomainResult

interface PdfRepository {
    suspend fun getPdfList(): DomainResult<List<PdfModel>>
}