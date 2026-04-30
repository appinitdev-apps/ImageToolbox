/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.CancelSmall: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Outlined.CancelSmall",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveToRelative(480f, 536f)
            lineToRelative(116f, 116f)
            quadToRelative(11f, 11f, 28f, 11f)
            reflectiveQuadToRelative(28f, -11f)
            quadToRelative(11f, -11f, 11f, -28f)
            reflectiveQuadToRelative(-11f, -28f)
            lineTo(536f, 480f)
            lineToRelative(116f, -116f)
            quadToRelative(11f, -11f, 11f, -28f)
            reflectiveQuadToRelative(-11f, -28f)
            quadToRelative(-11f, -11f, -28f, -11f)
            reflectiveQuadToRelative(-28f, 11f)
            lineTo(480f, 424f)
            lineTo(364f, 308f)
            quadToRelative(-11f, -11f, -28f, -11f)
            reflectiveQuadToRelative(-28f, 11f)
            quadToRelative(-11f, 11f, -11f, 28f)
            reflectiveQuadToRelative(11f, 28f)
            lineToRelative(116f, 116f)
            lineToRelative(-116f, 116f)
            quadToRelative(-11f, 11f, -11f, 28f)
            reflectiveQuadToRelative(11f, 28f)
            quadToRelative(11f, 11f, 28f, 11f)
            reflectiveQuadToRelative(28f, -11f)
            lineToRelative(116f, -116f)
            close()
            moveTo(480f, 480f)
            close()
        }
    }.build()
}