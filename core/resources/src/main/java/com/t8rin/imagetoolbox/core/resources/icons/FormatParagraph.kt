/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.FormatParagraph: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.FormatParagraph",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(371.5f, 788.5f)
            quadTo(360f, 777f, 360f, 760f)
            verticalLineToRelative(-200f)
            quadToRelative(-83f, 0f, -141.5f, -58.5f)
            reflectiveQuadTo(160f, 360f)
            quadToRelative(0f, -83f, 58.5f, -141.5f)
            reflectiveQuadTo(360f, 160f)
            horizontalLineToRelative(320f)
            quadToRelative(17f, 0f, 28.5f, 11.5f)
            reflectiveQuadTo(720f, 200f)
            quadToRelative(0f, 17f, -11.5f, 28.5f)
            reflectiveQuadTo(680f, 240f)
            horizontalLineToRelative(-40f)
            verticalLineToRelative(520f)
            quadToRelative(0f, 17f, -11.5f, 28.5f)
            reflectiveQuadTo(600f, 800f)
            quadToRelative(-17f, 0f, -28.5f, -11.5f)
            reflectiveQuadTo(560f, 760f)
            verticalLineToRelative(-520f)
            lineTo(440f, 240f)
            verticalLineToRelative(520f)
            quadToRelative(0f, 17f, -11.5f, 28.5f)
            reflectiveQuadTo(400f, 800f)
            quadToRelative(-17f, 0f, -28.5f, -11.5f)
            close()
        }
    }.build()
}