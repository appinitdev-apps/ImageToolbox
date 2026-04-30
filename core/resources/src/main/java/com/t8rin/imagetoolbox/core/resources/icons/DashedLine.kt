/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons


import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.DashedLine: ImageVector by lazy {
    ImageVector.Builder(
        name = "DashedLine",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(11.291f, 14.828f)
            lineToRelative(-4.242f, 4.242f)
            curveToRelative(-0.585f, 0.585f, -1.534f, 0.585f, -2.119f, 0f)
            lineToRelative(-0f, -0f)
            curveToRelative(-0.585f, -0.585f, -0.585f, -1.534f, 0f, -2.119f)
            lineToRelative(4.242f, -4.242f)
            curveToRelative(0.585f, -0.585f, 1.534f, -0.585f, 2.119f, 0f)
            lineToRelative(0f, 0f)
            curveTo(11.877f, 13.294f, 11.877f, 14.243f, 11.291f, 14.828f)
            close()
        }
        path(fill = SolidColor(Color.Black)) {
            moveTo(19.071f, 7.049f)
            lineToRelative(-4.242f, 4.242f)
            curveToRelative(-0.585f, 0.585f, -1.534f, 0.585f, -2.119f, 0f)
            lineToRelative(-0f, -0f)
            curveToRelative(-0.585f, -0.585f, -0.585f, -1.534f, 0f, -2.119f)
            lineToRelative(4.242f, -4.242f)
            curveToRelative(0.585f, -0.585f, 1.534f, -0.585f, 2.119f, 0f)
            lineToRelative(0f, 0f)
            curveTo(19.656f, 5.515f, 19.656f, 6.464f, 19.071f, 7.049f)
            close()
        }
    }.build()
}