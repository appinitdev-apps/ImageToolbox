/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.FilterFrames: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Outlined.FilterFrames",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(160f, 880f)
            quadToRelative(-33f, 0f, -56.5f, -23.5f)
            reflectiveQuadTo(80f, 800f)
            verticalLineToRelative(-560f)
            quadToRelative(0f, -33f, 23.5f, -56.5f)
            reflectiveQuadTo(160f, 160f)
            horizontalLineToRelative(160f)
            lineToRelative(132f, -132f)
            quadToRelative(12f, -12f, 28f, -12f)
            reflectiveQuadToRelative(28f, 12f)
            lineToRelative(132f, 132f)
            horizontalLineToRelative(160f)
            quadToRelative(33f, 0f, 56.5f, 23.5f)
            reflectiveQuadTo(880f, 240f)
            verticalLineToRelative(560f)
            quadToRelative(0f, 33f, -23.5f, 56.5f)
            reflectiveQuadTo(800f, 880f)
            lineTo(160f, 880f)
            close()
            moveTo(160f, 800f)
            horizontalLineToRelative(640f)
            verticalLineToRelative(-560f)
            lineTo(160f, 240f)
            verticalLineToRelative(560f)
            close()
            moveTo(240f, 680f)
            verticalLineToRelative(-320f)
            quadToRelative(0f, -17f, 11.5f, -28.5f)
            reflectiveQuadTo(280f, 320f)
            horizontalLineToRelative(400f)
            quadToRelative(17f, 0f, 28.5f, 11.5f)
            reflectiveQuadTo(720f, 360f)
            verticalLineToRelative(320f)
            quadToRelative(0f, 17f, -11.5f, 28.5f)
            reflectiveQuadTo(680f, 720f)
            lineTo(280f, 720f)
            quadToRelative(-17f, 0f, -28.5f, -11.5f)
            reflectiveQuadTo(240f, 680f)
            close()
            moveTo(320f, 640f)
            horizontalLineToRelative(320f)
            verticalLineToRelative(-240f)
            lineTo(320f, 400f)
            verticalLineToRelative(240f)
            close()
            moveTo(480f, 520f)
            close()
        }
    }.build()
}