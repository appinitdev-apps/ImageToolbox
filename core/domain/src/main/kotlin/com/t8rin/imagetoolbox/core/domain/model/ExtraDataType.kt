/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.model

sealed interface ExtraDataType {
    data object Gif : ExtraDataType
    data object Pdf : ExtraDataType
    data object File : ExtraDataType
    data object Audio : ExtraDataType

    data class Backup(val uri: String) : ExtraDataType
    data class Template(val uri: String) : ExtraDataType
    data class Text(val text: String) : ExtraDataType
}