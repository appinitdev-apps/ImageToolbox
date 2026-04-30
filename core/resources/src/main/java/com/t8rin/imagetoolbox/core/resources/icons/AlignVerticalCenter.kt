/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.AlignVerticalCenter: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.AlignVerticalCenter",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(340f, 840f)
            quadToRelative(-25f, 0f, -42.5f, -17.5f)
            reflectiveQuadTo(280f, 780f)
            verticalLineToRelative(-260f)
            lineTo(120f, 520f)
            quadToRelative(-17f, 0f, -28.5f, -11.5f)
            reflectiveQuadTo(80f, 480f)
            quadToRelative(0f, -17f, 11.5f, -28.5f)
            reflectiveQuadTo(120f, 440f)
            horizontalLineToRelative(160f)
            verticalLineToRelative(-260f)
            quadToRelative(0f, -25f, 17.5f, -42.5f)
            reflectiveQuadTo(340f, 120f)
            quadToRelative(25f, 0f, 42.5f, 17.5f)
            reflectiveQuadTo(400f, 180f)
            verticalLineToRelative(260f)
            horizontalLineToRelative(160f)
            verticalLineToRelative(-140f)
            quadToRelative(0f, -25f, 17.5f, -42.5f)
            reflectiveQuadTo(620f, 240f)
            quadToRelative(25f, 0f, 42.5f, 17.5f)
            reflectiveQuadTo(680f, 300f)
            verticalLineToRelative(140f)
            horizontalLineToRelative(160f)
            quadToRelative(17f, 0f, 28.5f, 11.5f)
            reflectiveQuadTo(880f, 480f)
            quadToRelative(0f, 17f, -11.5f, 28.5f)
            reflectiveQuadTo(840f, 520f)
            lineTo(680f, 520f)
            verticalLineToRelative(140f)
            quadToRelative(0f, 25f, -17.5f, 42.5f)
            reflectiveQuadTo(620f, 720f)
            quadToRelative(-25f, 0f, -42.5f, -17.5f)
            reflectiveQuadTo(560f, 660f)
            verticalLineToRelative(-140f)
            lineTo(400f, 520f)
            verticalLineToRelative(260f)
            quadToRelative(0f, 25f, -17.5f, 42.5f)
            reflectiveQuadTo(340f, 840f)
            close()
        }
    }.build()
}