/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.Boosty: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.Boosty",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(2.661f, 14.337f)
            lineTo(6.801f, 0f)
            horizontalLineToRelative(6.362f)
            lineTo(11.88f, 4.444f)
            lineToRelative(-0.038f, 0.077f)
            lineToRelative(-3.378f, 11.733f)
            horizontalLineToRelative(3.15f)
            curveToRelative(-1.321f, 3.289f, -2.35f, 5.867f, -3.086f, 7.733f)
            curveToRelative(-5.816f, -0.063f, -7.442f, -4.228f, -6.02f, -9.155f)
            moveTo(8.554f, 24f)
            lineToRelative(7.67f, -11.035f)
            horizontalLineToRelative(-3.25f)
            lineToRelative(2.83f, -7.073f)
            curveToRelative(4.852f, 0.508f, 7.137f, 4.33f, 5.791f, 8.952f)
            curveTo(20.16f, 19.81f, 14.344f, 24f, 8.68f, 24f)
            horizontalLineToRelative(-0.127f)
            close()
        }
    }.build()
}