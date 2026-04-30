/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.colors.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.t8rin.colors.util.ColorUtil

@Immutable
data class ColorData(val color: Color, val name: String) {
    val hexText: String = ColorUtil.colorToHex(color = color)
}