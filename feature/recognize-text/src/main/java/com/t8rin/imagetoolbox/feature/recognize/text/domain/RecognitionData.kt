/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.recognize.text.domain

data class RecognitionData(
    val text: String,
    val accuracy: Int,
    val hocr: String
) {
    companion object {
        val Empty = RecognitionData(
            text = "",
            accuracy = 0,
            hocr = ""
        )
    }
}