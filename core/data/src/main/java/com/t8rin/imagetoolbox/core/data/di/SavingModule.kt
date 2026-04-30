/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.di

import com.t8rin.imagetoolbox.core.data.saving.AndroidFileController
import com.t8rin.imagetoolbox.core.data.saving.AndroidFilenameCreator
import com.t8rin.imagetoolbox.core.data.saving.AndroidKeepAliveService
import com.t8rin.imagetoolbox.core.domain.image.MetadataProvider
import com.t8rin.imagetoolbox.core.domain.saving.FileController
import com.t8rin.imagetoolbox.core.domain.saving.FileController.Companion.toMetadataProvider
import com.t8rin.imagetoolbox.core.domain.saving.FilenameCreator
import com.t8rin.imagetoolbox.core.domain.saving.KeepAliveService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SavingModule {

    @Singleton
    @Binds
    fun provideFileController(
        impl: AndroidFileController
    ): FileController

    @Singleton
    @Binds
    fun filenameCreator(
        impl: AndroidFilenameCreator
    ): FilenameCreator

    @Singleton
    @Binds
    fun service(
        impl: AndroidKeepAliveService
    ): KeepAliveService

    companion object {
        @Singleton
        @Provides
        fun provideMetadata(
            impl: AndroidFileController
        ): MetadataProvider = impl.toMetadataProvider()
    }

}