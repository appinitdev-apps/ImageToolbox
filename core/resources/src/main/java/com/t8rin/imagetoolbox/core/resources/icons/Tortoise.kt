/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.Tortoise: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.Tortoise",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(19.31f, 5.6f)
            curveTo(18.09f, 5.56f, 16.88f, 6.5f, 16.5f, 8f)
            curveTo(16f, 10f, 16f, 10f, 15f, 11f)
            curveTo(13f, 13f, 10f, 14f, 4f, 15f)
            curveTo(3f, 15.16f, 2.5f, 15.5f, 2f, 16f)
            curveTo(4f, 16f, 6f, 16f, 4.5f, 17.5f)
            lineTo(3f, 19f)
            horizontalLineTo(6f)
            lineTo(8f, 17f)
            curveTo(10f, 18f, 11.33f, 18f, 13.33f, 17f)
            lineTo(14f, 19f)
            horizontalLineTo(17f)
            lineTo(16f, 16f)
            curveTo(16f, 16f, 17f, 12f, 18f, 11f)
            curveTo(19f, 10f, 19f, 11f, 20f, 11f)
            curveTo(21f, 11f, 22f, 10f, 22f, 8.5f)
            curveTo(22f, 8f, 22f, 7f, 20.5f, 6f)
            curveTo(20.15f, 5.76f, 19.74f, 5.62f, 19.31f, 5.6f)
            moveTo(9f, 6f)
            arcTo(6f, 6f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3f, 12f)
            curveTo(3f, 12.6f, 3.13f, 13.08f, 3.23f, 13.6f)
            curveTo(9.15f, 12.62f, 12.29f, 11.59f, 13.93f, 9.94f)
            lineTo(14.43f, 9.44f)
            curveTo(13.44f, 7.34f, 11.32f, 6f, 9f, 6f)
            close()
        }
    }.build()
}