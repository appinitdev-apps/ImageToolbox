/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.ZigzagLine: ImageVector by lazy {
    ImageVector.Builder(
        name = "ZigzagLine",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(19.011f, 5.493f)
            horizontalLineToRelative(-6f)
            curveToRelative(-0.828f, 0f, -1.499f, 0.671f, -1.499f, 1.499f)
            curveToRelative(0f, 0.002f, 0.001f, 0.004f, 0.001f, 0.006f)
            reflectiveCurveToRelative(-0.001f, 0.004f, -0.001f, 0.006f)
            verticalLineToRelative(4.495f)
            horizontalLineTo(6.989f)
            curveToRelative(-0.828f, 0f, -1.499f, 0.671f, -1.499f, 1.499f)
            curveToRelative(0f, 0.002f, 0.001f, 0.004f, 0.001f, 0.006f)
            reflectiveCurveToRelative(-0.001f, 0.004f, -0.001f, 0.006f)
            verticalLineToRelative(6f)
            curveToRelative(0f, 0.828f, 0.671f, 1.499f, 1.499f, 1.499f)
            reflectiveCurveToRelative(1.499f, -0.671f, 1.499f, -1.499f)
            verticalLineToRelative(-4.512f)
            horizontalLineToRelative(4.467f)
            curveToRelative(0.019f, 0.001f, 0.037f, 0.006f, 0.056f, 0.006f)
            curveToRelative(0.828f, 0f, 1.499f, -0.671f, 1.499f, -1.499f)
            verticalLineToRelative(-4.512f)
            horizontalLineToRelative(4.501f)
            curveToRelative(0.828f, 0f, 1.499f, -0.671f, 1.499f, -1.499f)
            reflectiveCurveTo(19.839f, 5.493f, 19.011f, 5.493f)
            close()
        }
    }.build()
}