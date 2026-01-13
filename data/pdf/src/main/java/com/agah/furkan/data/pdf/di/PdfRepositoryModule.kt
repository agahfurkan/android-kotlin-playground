package com.agah.furkan.data.pdf.di

import com.agah.furkan.data.pdf.PdfRepositoryImpl
import com.agah.furkan.domain.pdf.PdfRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PdfRepositoryModule {

    @Binds
    abstract fun providePdfRepository(pdfRepository: PdfRepositoryImpl): PdfRepository
}

