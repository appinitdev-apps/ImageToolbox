/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

sealed interface ExtractPagesAction {
    data class PagesCount(val count: Int) : ExtractPagesAction
    data class Progress(val index: Int, val image: Any) : ExtractPagesAction
}