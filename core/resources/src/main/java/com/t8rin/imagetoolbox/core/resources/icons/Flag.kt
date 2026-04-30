/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.Flag: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Outlined.Flag",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(280f, 560f)
            verticalLineToRelative(240f)
            quadToRelative(0f, 17f, -11.5f, 28.5f)
            reflectiveQuadTo(240f, 840f)
            quadToRelative(-17f, 0f, -28.5f, -11.5f)
            reflectiveQuadTo(200f, 800f)
            verticalLineToRelative(-600f)
            quadToRelative(0f, -17f, 11.5f, -28.5f)
            reflectiveQuadTo(240f, 160f)
            horizontalLineToRelative(287f)
            quadToRelative(14f, 0f, 25f, 9f)
            reflectiveQuadToRelative(14f, 23f)
            lineToRelative(10f, 48f)
            horizontalLineToRelative(184f)
            quadToRelative(17f, 0f, 28.5f, 11.5f)
            reflectiveQuadTo(800f, 280f)
            verticalLineToRelative(320f)
            quadToRelative(0f, 17f, -11.5f, 28.5f)
            reflectiveQuadTo(760f, 640f)
            lineTo(553f, 640f)
            quadToRelative(-14f, 0f, -25f, -9f)
            reflectiveQuadToRelative(-14f, -23f)
            lineToRelative(-10f, -48f)
            lineTo(280f, 560f)
            close()
            moveTo(586f, 560f)
            horizontalLineToRelative(134f)
            verticalLineToRelative(-240f)
            lineTo(543f, 320f)
            quadToRelative(-14f, 0f, -25f, -9f)
            reflectiveQuadToRelative(-14f, -23f)
            lineToRelative(-10f, -48f)
            lineTo(280f, 240f)
            verticalLineToRelative(240f)
            horizontalLineToRelative(257f)
            quadToRelative(14f, 0f, 25f, 9f)
            reflectiveQuadToRelative(14f, 23f)
            lineToRelative(10f, 48f)
            close()
            moveTo(500f, 400f)
            close()
        }
    }.build()
}