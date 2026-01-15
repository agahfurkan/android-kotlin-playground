package com.agah.furkan.data.pdf.kmp

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.pdf.Pdf
import com.agah.furkan.domain.pdf.PdfRepository
import com.agah.furkan.playgrounddatamodule.KmpPdf
import com.agah.furkan.playgrounddatamodule.StaticPdfData
import kotlinx.coroutines.delay
import javax.inject.Inject

class StaticPdfRepository @Inject constructor() : PdfRepository {

    override suspend fun getPdfList(): DomainResult<List<Pdf>> {
        // Simulate network delay (much shorter than the original 20 seconds)
        delay(400)

        val staticPdfs = StaticPdfData.getPdfList()

        return DomainResult.Success(
            staticPdfs.map { it.toDomainPdf() }
        )
    }

    // Mapper function
    private fun KmpPdf.toDomainPdf() = Pdf(
        data = data
    )
}

