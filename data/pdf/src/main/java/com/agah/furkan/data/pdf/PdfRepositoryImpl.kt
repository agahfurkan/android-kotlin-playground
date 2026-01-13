package com.agah.furkan.data.pdf

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.pdf.Pdf
import com.agah.furkan.domain.pdf.PdfRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class PdfRepositoryImpl @Inject constructor() : PdfRepository {
    override suspend fun getPdfList(): DomainResult<List<Pdf>> {
        delay(20000)
        return DomainResult.Success(
            listOf(
                Pdf(
                    "1"
                )
            )
        )
    }
}