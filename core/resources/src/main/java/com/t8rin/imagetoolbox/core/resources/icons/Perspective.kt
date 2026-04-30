/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.Perspective: ImageVector by lazy {
    ImageVector.Builder(
        name = "Perspective",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 1f
        ) {
            moveTo(5.892f, 7.771f)
            lineToRelative(12.216f, -1.879f)
            lineToRelative(0.94f, 12.216f)
            lineToRelative(-14.096f, -2.819f)
            lineTo(5.892f, 7.771f)
            close()
        }
        path(
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 1f
        ) {
            moveTo(4.482f, 7.771f)
            curveToRelative(0f, -0.778f, 0.631f, -1.41f, 1.41f, -1.41f)
            reflectiveCurveToRelative(1.41f, 0.631f, 1.41f, 1.41f)
            reflectiveCurveTo(6.67f, 9.181f, 5.892f, 9.181f)
            reflectiveCurveTo(4.482f, 8.55f, 4.482f, 7.771f)
        }
        path(
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 1f
        ) {
            moveTo(16.699f, 5.892f)
            curveToRelative(0f, -0.778f, 0.631f, -1.41f, 1.41f, -1.41f)
            reflectiveCurveToRelative(1.41f, 0.631f, 1.41f, 1.41f)
            reflectiveCurveToRelative(-0.631f, 1.41f, -1.41f, 1.41f)
            reflectiveCurveTo(16.699f, 6.67f, 16.699f, 5.892f)
        }
        path(
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 1f
        ) {
            moveTo(17.638f, 18.108f)
            curveToRelative(0f, -0.778f, 0.631f, -1.41f, 1.41f, -1.41f)
            reflectiveCurveToRelative(1.41f, 0.631f, 1.41f, 1.41f)
            reflectiveCurveToRelative(-0.631f, 1.41f, -1.41f, 1.41f)
            reflectiveCurveTo(17.638f, 18.887f, 17.638f, 18.108f)
        }
        path(
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 1f
        ) {
            moveTo(3.543f, 15.289f)
            curveToRelative(0f, -0.778f, 0.631f, -1.41f, 1.41f, -1.41f)
            reflectiveCurveToRelative(1.41f, 0.631f, 1.41f, 1.41f)
            reflectiveCurveToRelative(-0.631f, 1.41f, -1.41f, 1.41f)
            reflectiveCurveTo(3.543f, 16.067f, 3.543f, 15.289f)
        }
    }.build()
}