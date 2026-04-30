/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.Title: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.Title",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(420f, 280f)
            lineTo(260f, 280f)
            quadToRelative(-25f, 0f, -42.5f, -17.5f)
            reflectiveQuadTo(200f, 220f)
            quadToRelative(0f, -25f, 17.5f, -42.5f)
            reflectiveQuadTo(260f, 160f)
            horizontalLineToRelative(440f)
            quadToRelative(25f, 0f, 42.5f, 17.5f)
            reflectiveQuadTo(760f, 220f)
            quadToRelative(0f, 25f, -17.5f, 42.5f)
            reflectiveQuadTo(700f, 280f)
            lineTo(540f, 280f)
            verticalLineToRelative(460f)
            quadToRelative(0f, 25f, -17.5f, 42.5f)
            reflectiveQuadTo(480f, 800f)
            quadToRelative(-25f, 0f, -42.5f, -17.5f)
            reflectiveQuadTo(420f, 740f)
            verticalLineToRelative(-460f)
            close()
        }
    }.build()
}