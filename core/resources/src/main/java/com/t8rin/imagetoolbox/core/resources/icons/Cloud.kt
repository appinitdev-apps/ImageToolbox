/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.Cloud: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.Cloud",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(260f, 800f)
            quadToRelative(-91f, 0f, -155.5f, -63f)
            reflectiveQuadTo(40f, 583f)
            quadToRelative(0f, -78f, 47f, -139f)
            reflectiveQuadToRelative(123f, -78f)
            quadToRelative(25f, -92f, 100f, -149f)
            reflectiveQuadToRelative(170f, -57f)
            quadToRelative(117f, 0f, 198.5f, 81.5f)
            reflectiveQuadTo(760f, 440f)
            quadToRelative(69f, 8f, 114.5f, 59.5f)
            reflectiveQuadTo(920f, 620f)
            quadToRelative(0f, 75f, -52.5f, 127.5f)
            reflectiveQuadTo(740f, 800f)
            lineTo(260f, 800f)
            close()
        }
    }.build()
}