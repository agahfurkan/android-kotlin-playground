package com.agah.furkan.data.pdf.di

import com.agah.furkan.data.pdf.PdfRepositoryImpl
import com.agah.furkan.data.pdf.kmp.StaticPdfRepository
import com.agah.furkan.domain.pdf.PdfRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PdfRepositoryModule {
    @Provides
    @Singleton
    fun providePdfRepository(
        @Named("flavor") flavor: String,
        staticPdfRepository: StaticPdfRepository,
        pdfRepository: PdfRepositoryImpl
    ): PdfRepository {
        return if (flavor == "demo") {
            staticPdfRepository
        } else {
            pdfRepository
        }
    }
}

