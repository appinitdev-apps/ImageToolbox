/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.Label: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.Label",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f,
        autoMirror = true
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(807f, 526f)
            lineTo(666f, 726f)
            quadToRelative(-11f, 16f, -28.5f, 25f)
            reflectiveQuadToRelative(-37.5f, 9f)
            lineTo(200f, 760f)
            quadToRelative(-33f, 0f, -56.5f, -23.5f)
            reflectiveQuadTo(120f, 680f)
            verticalLineToRelative(-400f)
            quadToRelative(0f, -33f, 23.5f, -56.5f)
            reflectiveQuadTo(200f, 200f)
            horizontalLineToRelative(400f)
            quadToRelative(20f, 0f, 37.5f, 9f)
            reflectiveQuadToRelative(28.5f, 25f)
            lineToRelative(141f, 200f)
            quadToRelative(15f, 21f, 15f, 46f)
            reflectiveQuadToRelative(-15f, 46f)
            close()
        }
    }.build()
}