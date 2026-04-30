/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.data.project

import com.t8rin.imagetoolbox.feature.markup_layers.domain.MarkupProjectResult

internal sealed interface ProjectFileLoadResult {
    data class Success(
        val projectFile: MarkupProjectFile
    ) : ProjectFileLoadResult

    data class Error(
        val error: MarkupProjectResult.Error
    ) : ProjectFileLoadResult
}