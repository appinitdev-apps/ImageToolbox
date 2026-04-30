/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.widget

import android.graphics.Bitmap
import com.t8rin.trickle.TrickleUtils

internal fun calculateBrightnessEstimate(
    bitmap: Bitmap,
    pixelSpacing: Int = 1
): Int = TrickleUtils.calculateBrightness(
    bitmap = bitmap,
    pixelSpacing = pixelSpacing
)