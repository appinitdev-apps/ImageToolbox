/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.domain

sealed interface MarkupProjectResult {

    data class Success(
        val project: MarkupProject
    ) : MarkupProjectResult

    sealed class Error(
        open val message: String
    ) : MarkupProjectResult {
        data class InvalidArchive(
            override val message: String
        ) : Error(message)

        data class MissingProjectFile(
            override val message: String
        ) : Error(message)

        data class InvalidProjectFile(
            override val message: String
        ) : Error(message)

        data class UnsupportedVersion(
            val version: Int,
            override val message: String
        ) : Error(message)

        data class Exception(
            val throwable: Throwable,
            override val message: String
        ) : Error(message)
    }
}