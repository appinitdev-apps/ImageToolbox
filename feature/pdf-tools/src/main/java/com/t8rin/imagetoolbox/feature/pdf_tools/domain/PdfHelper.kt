/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain

import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfCheckResult
import kotlinx.coroutines.flow.StateFlow

interface PdfHelper {

    val savedSignatures: StateFlow<List<String>>

    suspend fun saveSignature(signature: Any): Boolean

    fun setMasterPassword(
        password: String?
    )

    fun createTempName(
        key: String,
        uri: String? = null
    ): String

    fun clearPdfCache(uri: String?)

    suspend fun checkPdf(
        uri: String
    ): PdfCheckResult

    suspend fun getPdfPages(
        uri: String
    ): List<Int>

    suspend fun getPdfPageSizes(
        uri: String
    ): List<IntegerSize>

}