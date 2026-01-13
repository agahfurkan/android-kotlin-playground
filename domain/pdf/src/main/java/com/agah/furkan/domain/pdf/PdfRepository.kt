package com.agah.furkan.domain.pdf

import com.agah.furkan.core.domain.model.DomainResult

interface PdfRepository {
    suspend fun getPdfList(): DomainResult<List<Pdf>>
}

