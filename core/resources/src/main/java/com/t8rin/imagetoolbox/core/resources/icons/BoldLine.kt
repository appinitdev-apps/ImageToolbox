/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.BoldLine: ImageVector by lazy {
    ImageVector.Builder(
        name = "BoldLine",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(19.071f, 4.929f)
            curveToRelative(-0.585f, -0.585f, -1.534f, -0.585f, -2.12f, 0f)
            lineTo(12.709f, 9.172f)
            curveToRelative(-0f, 0f, -0f, 0f, -0f, 0f)
            lineToRelative(-3.537f, 3.537f)
            curveToRelative(-0f, 0f, -0f, 0f, -0f, 0f)
            lineToRelative(-4.242f, 4.242f)
            curveToRelative(-0.585f, 0.585f, -0.585f, 1.534f, 0f, 2.119f)
            curveToRelative(0.585f, 0.585f, 1.534f, 0.585f, 2.12f, 0f)
            lineToRelative(4.242f, -4.242f)
            curveToRelative(0f, -0f, 0f, -0f, 0f, -0f)
            lineToRelative(3.537f, -3.537f)
            lineToRelative(4.242f, -4.242f)
            curveTo(19.656f, 6.464f, 19.656f, 5.515f, 19.071f, 4.929f)
            close()
        }
    }.build()
}