/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.FolderOff: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Outlined.FolderOff",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(880f, 320f)
            verticalLineToRelative(351f)
            quadToRelative(0f, 20f, -12.5f, 30f)
            reflectiveQuadTo(840f, 711f)
            quadToRelative(-15f, 0f, -27.5f, -10.5f)
            reflectiveQuadTo(800f, 670f)
            verticalLineToRelative(-350f)
            lineTo(467f, 320f)
            quadToRelative(-16f, 0f, -30.5f, -6f)
            reflectiveQuadTo(411f, 297f)
            lineToRelative(-68f, -68f)
            quadToRelative(-14f, -14f, -12.5f, -30f)
            reflectiveQuadToRelative(12.5f, -27f)
            quadToRelative(11f, -11f, 27f, -12.5f)
            reflectiveQuadToRelative(30f, 12.5f)
            lineToRelative(68f, 68f)
            horizontalLineToRelative(332f)
            quadToRelative(33f, 0f, 56.5f, 23.5f)
            reflectiveQuadTo(880f, 320f)
            close()
            moveTo(160f, 800f)
            quadToRelative(-33f, 0f, -56.5f, -23.5f)
            reflectiveQuadTo(80f, 720f)
            verticalLineToRelative(-480f)
            quadToRelative(0f, -33f, 23.5f, -56.5f)
            reflectiveQuadTo(160f, 160f)
            lineToRelative(80f, 80f)
            horizontalLineToRelative(-80f)
            verticalLineToRelative(480f)
            horizontalLineToRelative(447f)
            lineTo(56f, 168f)
            quadToRelative(-11f, -11f, -11.5f, -27.5f)
            reflectiveQuadTo(56f, 112f)
            quadToRelative(11f, -11f, 28f, -11f)
            reflectiveQuadToRelative(28f, 11f)
            lineToRelative(736f, 736f)
            quadToRelative(12f, 12f, 11.5f, 28f)
            reflectiveQuadTo(847f, 904f)
            quadToRelative(-12f, 11f, -28f, 11.5f)
            reflectiveQuadTo(791f, 904f)
            lineTo(687f, 800f)
            lineTo(160f, 800f)
            close()
            moveTo(368f, 480f)
            close()
            moveTo(577f, 463f)
            close()
        }
    }.build()
}