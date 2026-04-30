/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain

import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.ExtractPagesAction
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfCreationParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfCropParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfExtractPagesParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfMetadata
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfPageNumbersParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfRemoveAnnotationParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfSignatureParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfWatermarkParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PrintPdfParams
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.SearchablePdfPage
import kotlinx.coroutines.flow.Flow

interface PdfManager : PdfHelper {

    fun extractPages(
        uri: String,
        params: PdfExtractPagesParams
    ): Flow<ExtractPagesAction>

    suspend fun createPdf(
        imageUris: List<String>,
        params: PdfCreationParams
    ): String

    suspend fun createSearchablePdf(
        pages: List<SearchablePdfPage>,
        params: PdfCreationParams = PdfCreationParams(quality = 100)
    ): String

    suspend fun mergePdfs(
        uris: List<String>
    ): String

    suspend fun splitPdf(
        uri: String,
        pages: List<Int>?
    ): String

    suspend fun removePdfPages(
        uri: String,
        pages: List<Int>
    ): String

    suspend fun rotatePdf(
        uri: String,
        rotations: List<Int>
    ): String

    suspend fun rearrangePdf(
        uri: String,
        newOrder: List<Int>
    ): String

    suspend fun addPageNumbers(
        uri: String,
        params: PdfPageNumbersParams
    ): String

    suspend fun addWatermark(
        uri: String,
        params: PdfWatermarkParams
    ): String

    suspend fun addSignature(
        uri: String,
        params: PdfSignatureParams
    ): String

    suspend fun protectPdf(
        uri: String,
        password: String
    ): String

    suspend fun unlockPdf(
        uri: String,
        password: String
    ): String

    suspend fun extractPagesFromPdf(
        uri: String
    ): List<String>

    suspend fun compressPdf(
        uri: String,
        quality: Float
    ): String

    suspend fun convertToGrayscale(
        uri: String
    ): String

    suspend fun repairPdf(
        uri: String
    ): String

    suspend fun changePdfMetadata(
        uri: String,
        metadata: PdfMetadata?
    ): String

    suspend fun getPdfMetadata(
        uri: String
    ): PdfMetadata

    suspend fun stripText(
        uri: String
    ): List<String>

    suspend fun cropPdf(
        uri: String,
        params: PdfCropParams
    ): String

    suspend fun flattenPdf(
        uri: String,
        quality: Float
    ): String

    suspend fun detectPdfAutoRotations(
        uri: String
    ): List<Int>

    suspend fun extractImagesFromPdf(
        uri: String
    ): String?

    suspend fun convertToZip(
        uri: String,
        interval: Int
    ): String

    suspend fun printPdf(
        uri: String,
        params: PrintPdfParams
    ): String

    suspend fun removeAnnotations(
        uri: String,
        params: PdfRemoveAnnotationParams
    ): String

}