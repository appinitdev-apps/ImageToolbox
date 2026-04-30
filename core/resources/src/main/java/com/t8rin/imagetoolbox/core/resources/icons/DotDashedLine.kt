/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.DotDashedLine: ImageVector by lazy {
    ImageVector.Builder(
        name = "DotDashedLine",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(9.877f, 16.243f)
            lineToRelative(-2.828f, 2.828f)
            curveToRelative(-0.585f, 0.585f, -1.534f, 0.585f, -2.119f, 0f)
            lineToRelative(-0f, -0f)
            curveToRelative(-0.585f, -0.585f, -0.585f, -1.534f, -0f, -2.119f)
            lineToRelative(2.828f, -2.828f)
            curveToRelative(0.585f, -0.585f, 1.534f, -0.585f, 2.119f, 0f)
            lineToRelative(0f, 0f)
            curveTo(10.462f, 14.708f, 10.462f, 15.657f, 9.877f, 16.243f)
            close()
        }
        path(fill = SolidColor(Color.Black)) {
            moveTo(19.071f, 7.049f)
            lineToRelative(-2.83f, 2.83f)
            curveToRelative(-0.585f, 0.585f, -1.534f, 0.585f, -2.119f, 0f)
            lineToRelative(-0f, -0f)
            curveToRelative(-0.585f, -0.585f, -0.585f, -1.534f, 0f, -2.119f)
            lineToRelative(2.83f, -2.83f)
            curveToRelative(0.585f, -0.585f, 1.534f, -0.585f, 2.119f, 0f)
            lineToRelative(0f, 0f)
            curveTo(19.656f, 5.515f, 19.656f, 6.464f, 19.071f, 7.049f)
            close()
        }
        path(fill = SolidColor(Color.Black)) {
            moveTo(13.06f, 13.06f)
            lineToRelative(-0f, 0f)
            curveToRelative(-0.585f, 0.585f, -1.534f, 0.585f, -2.119f, 0f)
            lineToRelative(-0f, -0f)
            curveToRelative(-0.585f, -0.585f, -0.585f, -1.534f, 0f, -2.119f)
            lineToRelative(0f, -0f)
            curveToRelative(0.585f, -0.585f, 1.534f, -0.585f, 2.119f, 0f)
            lineToRelative(0f, 0f)
            curveTo(13.645f, 11.526f, 13.645f, 12.474f, 13.06f, 13.06f)
            close()
        }
    }.build()
}