/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

enum class PdfAnnotationType {
    Link,
    FileAttachment,
    Line,
    Popup,
    Stamp,
    SquareCircle,
    Text,
    TextMarkup,
    Widget,
    Markup,
    Unknown;

    companion object {
        val setEntries by lazy {
            entries.toSet()
        }
    }
}