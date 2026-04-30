/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.Padding: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Outlined.Padding",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(320f, 360f)
            quadToRelative(17f, 0f, 28.5f, -11.5f)
            reflectiveQuadTo(360f, 320f)
            quadToRelative(0f, -17f, -11.5f, -28.5f)
            reflectiveQuadTo(320f, 280f)
            quadToRelative(-17f, 0f, -28.5f, 11.5f)
            reflectiveQuadTo(280f, 320f)
            quadToRelative(0f, 17f, 11.5f, 28.5f)
            reflectiveQuadTo(320f, 360f)
            close()
            moveTo(480f, 360f)
            quadToRelative(17f, 0f, 28.5f, -11.5f)
            reflectiveQuadTo(520f, 320f)
            quadToRelative(0f, -17f, -11.5f, -28.5f)
            reflectiveQuadTo(480f, 280f)
            quadToRelative(-17f, 0f, -28.5f, 11.5f)
            reflectiveQuadTo(440f, 320f)
            quadToRelative(0f, 17f, 11.5f, 28.5f)
            reflectiveQuadTo(480f, 360f)
            close()
            moveTo(640f, 360f)
            quadToRelative(17f, 0f, 28.5f, -11.5f)
            reflectiveQuadTo(680f, 320f)
            quadToRelative(0f, -17f, -11.5f, -28.5f)
            reflectiveQuadTo(640f, 280f)
            quadToRelative(-17f, 0f, -28.5f, 11.5f)
            reflectiveQuadTo(600f, 320f)
            quadToRelative(0f, 17f, 11.5f, 28.5f)
            reflectiveQuadTo(640f, 360f)
            close()
            moveTo(200f, 840f)
            quadToRelative(-33f, 0f, -56.5f, -23.5f)
            reflectiveQuadTo(120f, 760f)
            verticalLineToRelative(-560f)
            quadToRelative(0f, -33f, 23.5f, -56.5f)
            reflectiveQuadTo(200f, 120f)
            horizontalLineToRelative(560f)
            quadToRelative(33f, 0f, 56.5f, 23.5f)
            reflectiveQuadTo(840f, 200f)
            verticalLineToRelative(560f)
            quadToRelative(0f, 33f, -23.5f, 56.5f)
            reflectiveQuadTo(760f, 840f)
            lineTo(200f, 840f)
            close()
            moveTo(200f, 760f)
            horizontalLineToRelative(560f)
            verticalLineToRelative(-560f)
            lineTo(200f, 200f)
            verticalLineToRelative(560f)
            close()
            moveTo(200f, 200f)
            verticalLineToRelative(560f)
            verticalLineToRelative(-560f)
            close()
        }
    }.build()
}