/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.di

import com.t8rin.imagetoolbox.feature.pdf_tools.data.AndroidPdfManager
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.PdfManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface PdfToolsModule {

    @Singleton
    @Binds
    fun providePdfManager(
        manager: AndroidPdfManager
    ): PdfManager

}