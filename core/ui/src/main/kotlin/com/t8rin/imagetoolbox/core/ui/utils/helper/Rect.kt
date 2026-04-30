/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import androidx.compose.ui.geometry.Rect
import com.t8rin.imagetoolbox.core.domain.model.RectModel

fun Rect.toModel() = RectModel(
    left = left,
    top = top,
    right = right,
    bottom = bottom
)