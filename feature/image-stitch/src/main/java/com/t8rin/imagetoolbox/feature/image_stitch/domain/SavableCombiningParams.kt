/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.image_stitch.domain

import com.t8rin.imagetoolbox.core.domain.image.model.BlendingMode
import com.t8rin.imagetoolbox.core.ui.utils.helper.entries

data class SavableCombiningParams(
    val stitchMode: String,
    val spacing: Int,
    val scaleSmallImagesToLarge: Boolean,
    val backgroundColor: Int,
    val fadingEdgesMode: StitchFadeSide,
    val alignment: StitchAlignment,
    val outputScale: Float,
    val blendingMode: Int,
    val fadeStrength: Float
)

fun CombiningParams.toSavable() = SavableCombiningParams(
    stitchMode = "${stitchMode.ordinal}_${stitchMode.gridCellsCount()}_${
        stitchMode.drops().joinToString(separator = "_")
    }",
    spacing = spacing,
    scaleSmallImagesToLarge = scaleSmallImagesToLarge,
    backgroundColor = backgroundColor,
    fadingEdgesMode = fadingEdgesMode,
    alignment = alignment,
    outputScale = outputScale,
    blendingMode = blendingMode.value,
    fadeStrength = fadeStrength
)

fun SavableCombiningParams.toParams() = CombiningParams(
    stitchMode = stitchMode.split("_").let {
        if (it.size < 2) StitchMode.Horizontal
        else {
            val mode = StitchMode.fromOrdinal(it.getOrNull(0)?.toIntOrNull() ?: 0)
            val cells = it.getOrNull(1)?.toIntOrNull() ?: 0

            when (mode) {
                is StitchMode.Grid.Horizontal -> mode.copy(rows = cells)
                is StitchMode.Grid.Vertical -> mode.copy(columns = cells)
                is StitchMode.Auto -> StitchMode.Auto(it.drop(2).map { s -> s.toIntOrNull() ?: 0 })

                else -> mode
            }
        }
    },
    spacing = spacing,
    scaleSmallImagesToLarge = scaleSmallImagesToLarge,
    backgroundColor = backgroundColor,
    fadingEdgesMode = fadingEdgesMode,
    alignment = alignment,
    outputScale = outputScale,
    blendingMode = BlendingMode.entries.find { it.value == blendingMode } ?: BlendingMode.SrcOver,
    fadeStrength = fadeStrength
)