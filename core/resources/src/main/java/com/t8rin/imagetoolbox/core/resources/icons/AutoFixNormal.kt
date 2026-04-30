/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.AutoFixNormal: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.AutoFixNormal",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveToRelative(19.05f, 4.95f)
            lineToRelative(-1.075f, -0.5f)
            quadTo(17.7f, 4.325f, 17.7f, 4f)
            reflectiveQuadToRelative(0.275f, -0.45f)
            lineToRelative(1.075f, -0.5f)
            lineToRelative(0.5f, -1.075f)
            quadToRelative(0.125f, -0.275f, 0.45f, -0.275f)
            reflectiveQuadToRelative(0.45f, 0.275f)
            lineToRelative(0.5f, 1.075f)
            lineToRelative(1.075f, 0.5f)
            quadToRelative(0.275f, 0.125f, 0.275f, 0.45f)
            reflectiveQuadToRelative(-0.275f, 0.45f)
            lineToRelative(-1.075f, 0.5f)
            lineToRelative(-0.5f, 1.075f)
            quadToRelative(-0.125f, 0.275f, -0.45f, 0.275f)
            reflectiveQuadToRelative(-0.45f, -0.275f)
            close()
            moveTo(5.1f, 21.7f)
            lineToRelative(-2.8f, -2.8f)
            quadToRelative(-0.3f, -0.3f, -0.3f, -0.725f)
            reflectiveQuadToRelative(0.3f, -0.725f)
            lineTo(13.45f, 6.3f)
            quadToRelative(0.3f, -0.3f, 0.725f, -0.3f)
            reflectiveQuadToRelative(0.725f, 0.3f)
            lineToRelative(2.8f, 2.8f)
            quadToRelative(0.3f, 0.3f, 0.3f, 0.725f)
            reflectiveQuadToRelative(-0.3f, 0.725f)
            lineTo(6.55f, 21.7f)
            quadToRelative(-0.3f, 0.3f, -0.725f, 0.3f)
            reflectiveQuadToRelative(-0.725f, -0.3f)
            close()
            moveTo(14.175f, 11.225f)
            lineTo(15.575f, 9.825f)
            lineTo(14.175f, 8.425f)
            lineTo(12.775f, 9.825f)
            close()
        }
    }.build()
}