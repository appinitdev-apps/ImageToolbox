/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.ArrowCircleRight: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.ArrowCircleRight",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(480f, 880f)
            quadToRelative(-83f, 0f, -156f, -31.5f)
            reflectiveQuadTo(197f, 763f)
            quadToRelative(-54f, -54f, -85.5f, -127f)
            reflectiveQuadTo(80f, 480f)
            quadToRelative(0f, -83f, 31.5f, -156f)
            reflectiveQuadTo(197f, 197f)
            quadToRelative(54f, -54f, 127f, -85.5f)
            reflectiveQuadTo(480f, 80f)
            quadToRelative(83f, 0f, 156f, 31.5f)
            reflectiveQuadTo(763f, 197f)
            quadToRelative(54f, 54f, 85.5f, 127f)
            reflectiveQuadTo(880f, 480f)
            quadToRelative(0f, 83f, -31.5f, 156f)
            reflectiveQuadTo(763f, 763f)
            quadToRelative(-54f, 54f, -127f, 85.5f)
            reflectiveQuadTo(480f, 880f)
            close()
            moveTo(488f, 520f)
            lineTo(452f, 556f)
            quadToRelative(-11f, 11f, -11f, 28f)
            reflectiveQuadToRelative(11f, 28f)
            quadToRelative(11f, 11f, 28f, 11f)
            reflectiveQuadToRelative(28f, -11f)
            lineToRelative(104f, -104f)
            quadToRelative(12f, -12f, 12f, -28f)
            reflectiveQuadToRelative(-12f, -28f)
            lineTo(508f, 348f)
            quadToRelative(-11f, -11f, -28f, -11f)
            reflectiveQuadToRelative(-28f, 11f)
            quadToRelative(-11f, 11f, -11f, 28f)
            reflectiveQuadToRelative(11f, 28f)
            lineToRelative(36f, 36f)
            lineTo(360f, 440f)
            quadToRelative(-17f, 0f, -28.5f, 11.5f)
            reflectiveQuadTo(320f, 480f)
            quadToRelative(0f, 17f, 11.5f, 28.5f)
            reflectiveQuadTo(360f, 520f)
            horizontalLineToRelative(128f)
            close()
        }
    }.build()
}