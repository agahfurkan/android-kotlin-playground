package com.agah.furkan.data.pdf

import com.agah.furkan.core.data.model.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

class PdfRepositoryImpl @Inject constructor() : PdfRepository {
    override suspend fun getPdfList(): Result<List<PdfModel>> {
        delay(20000)
        return Result.Success(
            listOf(
                PdfModel(
                    "1"
                )
            )
        )
    }
}