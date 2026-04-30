/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.domain.model

data class PrintPdfParams(
    val orientation: PageOrientation = PageOrientation.ORIGINAL,
    val pageSize: PageSize = PageSize.Auto,
    val pagesPerSheet: Int = 1,
    val marginPercent: Float = 0f,
    val quality: Float = 0.85f,
) {
    val pageSizeFinal = if (pageSize == PageSize.Auto) {
        null
    } else {
        when (orientation) {
            PageOrientation.ORIGINAL -> null
            PageOrientation.VERTICAL -> pageSize
            PageOrientation.HORIZONTAL -> pageSize.run { copy(width = height, height = width) }
        }
    }

    val gridSize = pagesMapping.getOrDefault(pagesPerSheet, 1 to 1).let {
        when (orientation) {
            PageOrientation.ORIGINAL,
            PageOrientation.VERTICAL -> it

            PageOrientation.HORIZONTAL -> it.second to it.first
        }
    }

    companion object {
        val pagesMapping by lazy {
            mapOf(
                1 to (1 to 1),
                2 to (2 to 1),
                4 to (2 to 2),
                6 to (3 to 2),
                8 to (4 to 2),
                9 to (3 to 3),
                12 to (4 to 3),
                16 to (4 to 4)
            )
        }

        val pageRange by lazy {
            pagesMapping.keys.sorted().run { first()..last() }
        }
    }
}