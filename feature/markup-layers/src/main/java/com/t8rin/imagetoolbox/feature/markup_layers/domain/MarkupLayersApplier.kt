/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.domain

import com.t8rin.imagetoolbox.core.domain.saving.io.Writeable

interface MarkupLayersApplier<I> {

    suspend fun applyToImage(
        image: I,
        layers: List<MarkupLayer>
    ): I

    suspend fun saveProject(
        destination: Writeable,
        project: MarkupProject
    )

    suspend fun openProject(
        uri: String
    ): MarkupProjectResult

    fun clearProjectCache()

}