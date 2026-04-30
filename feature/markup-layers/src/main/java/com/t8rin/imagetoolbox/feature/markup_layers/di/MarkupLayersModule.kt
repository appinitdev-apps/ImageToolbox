/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.di

import android.graphics.Bitmap
import com.t8rin.imagetoolbox.feature.markup_layers.data.AndroidMarkupLayersApplier
import com.t8rin.imagetoolbox.feature.markup_layers.domain.MarkupLayersApplier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface MarkupLayersModule {

    @Binds
    @Singleton
    fun applier(
        impl: AndroidMarkupLayersApplier
    ): MarkupLayersApplier<Bitmap>

}