/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.image_splitting.domain

import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.domain.image.model.Quality

data class SplitParams(
    val rowsCount: Int,
    val columnsCount: Int,
    val rowPercentages: List<Float>,
    val columnPercentages: List<Float>,
    val imageFormat: ImageFormat,
    val quality: Quality,
) {
    fun withAspectRatio(
        targetAspectRatio: Float,
        maxRows: Int = rowsCount,
        maxColumns: Int = columnsCount
    ): SplitParams {
        require(targetAspectRatio > 0f) { "aspectRatio must be > 0" }

        var bestRows = 1
        var bestCols = 1

        for (rows in 1..maxRows) {
            val tileHeight = 1f / rows
            val tileWidth = tileHeight * targetAspectRatio
            val cols = (1f / tileWidth).toInt()
            if (cols in 1..maxColumns) {
                bestRows = rows
                bestCols = cols
            }
        }

        val rowsCount = bestRows
        val columnsCount = bestCols

        val rowPercentages = List(rowsCount) { 1f / rowsCount }.let { rows ->
            rows.dropLast(1) + (1f - rows.dropLast(1).sum())
        }

        val columnPercentages = List(columnsCount) { 1f / columnsCount }.let { cols ->
            cols.dropLast(1) + (1f - cols.dropLast(1).sum())
        }


        return this.copy(
            rowsCount = rowsCount,
            columnsCount = columnsCount,
            rowPercentages = rowPercentages,
            columnPercentages = columnPercentages
        )
    }

    companion object {
        val Default by lazy {
            SplitParams(
                rowsCount = 2,
                columnsCount = 2,
                rowPercentages = emptyList(),
                columnPercentages = emptyList(),
                imageFormat = ImageFormat.Default,
                quality = Quality.Base()
            )
        }
    }
}