package com.agah.furkan.data.pdf

import com.agah.furkan.core.domain.model.DomainResult
import kotlinx.coroutines.delay
import javax.inject.Inject

class PdfRepositoryImpl @Inject constructor() : PdfRepository {
    override suspend fun getPdfList(): DomainResult<List<PdfModel>> {
        delay(20000)
        return DomainResult.Success(
            listOf(
                PdfModel(
                    "1"
                )
            )
        )
    }
}