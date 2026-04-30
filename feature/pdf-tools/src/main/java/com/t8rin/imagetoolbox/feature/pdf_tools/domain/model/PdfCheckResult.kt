/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

sealed interface PdfCheckResult {
    data class Failure(val throwable: Throwable) : PdfCheckResult

    data object Open : PdfCheckResult

    sealed interface Protected : PdfCheckResult {
        data object NeedsPassword : Protected
        data class Unlocked(val decryptedUri: String) : Protected
    }
}