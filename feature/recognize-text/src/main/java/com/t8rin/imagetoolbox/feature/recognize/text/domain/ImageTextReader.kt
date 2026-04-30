/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.recognize.text.domain

import com.t8rin.imagetoolbox.core.domain.remote.DownloadProgress

interface ImageTextReader {

    suspend fun getTextFromImage(
        type: RecognitionType,
        languageCode: String,
        segmentationMode: SegmentationMode,
        ocrEngineMode: OcrEngineMode,
        parameters: TessParams,
        model: Any?,
        onProgress: (Int) -> Unit
    ): TextRecognitionResult

    suspend fun downloadTrainingData(
        type: RecognitionType,
        languageCode: String,
        onProgress: (DownloadProgress) -> Unit
    )

    fun isLanguageDataExists(
        type: RecognitionType,
        languageCode: String
    ): Boolean

    suspend fun getLanguages(
        type: RecognitionType
    ): List<OCRLanguage>

    fun getLanguageForCode(
        code: String
    ): OCRLanguage

    suspend fun deleteLanguage(
        language: OCRLanguage,
        types: List<RecognitionType>
    )

    suspend fun exportLanguagesToZip(): String?

    suspend fun importLanguagesFromUri(
        zipUri: String
    ): Result<Any>

}