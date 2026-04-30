/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.di

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.feature.ai_tools.data.AndroidAiToolsRepository
import com.t8rin.imagetoolbox.feature.ai_tools.domain.AiToolsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AiToolsModule {

    @Binds
    @Singleton
    fun repository(impl: AndroidAiToolsRepository): AiToolsRepository<Bitmap>

}