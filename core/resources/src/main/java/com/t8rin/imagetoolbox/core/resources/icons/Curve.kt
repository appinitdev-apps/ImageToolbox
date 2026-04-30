/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons


import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.Curve: ImageVector by lazy {
    ImageVector.Builder(
        name = "Curve",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(9.96f, 11.31f)
            curveTo(10.82f, 8.1f, 11.5f, 6f, 13f, 6f)
            curveTo(14.5f, 6f, 15.18f, 8.1f, 16.04f, 11.31f)
            curveTo(17f, 14.92f, 18.1f, 19f, 22f, 19f)
            verticalLineTo(17f)
            curveTo(19.8f, 17f, 19f, 14.54f, 17.97f, 10.8f)
            curveTo(17.08f, 7.46f, 16.15f, 4f, 13f, 4f)
            curveTo(9.85f, 4f, 8.92f, 7.46f, 8.03f, 10.8f)
            curveTo(7.03f, 14.54f, 6.2f, 17f, 4f, 17f)
            verticalLineTo(2f)
            horizontalLineTo(2f)
            verticalLineTo(22f)
            horizontalLineTo(22f)
            verticalLineTo(20f)
            horizontalLineTo(4f)
            verticalLineTo(19f)
            curveTo(7.9f, 19f, 9f, 14.92f, 9.96f, 11.31f)
            close()
        }
    }.build()
}