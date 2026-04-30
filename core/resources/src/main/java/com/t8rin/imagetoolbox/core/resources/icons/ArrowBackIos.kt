/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.ArrowBackIos: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.ArrowBackIos",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f,
        autoMirror = true
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveToRelative(142f, 480f)
            lineToRelative(294f, 294f)
            quadToRelative(15f, 15f, 14.5f, 35f)
            reflectiveQuadTo(435f, 844f)
            quadToRelative(-15f, 15f, -35f, 15f)
            reflectiveQuadToRelative(-35f, -15f)
            lineTo(57f, 537f)
            quadToRelative(-12f, -12f, -18f, -27f)
            reflectiveQuadToRelative(-6f, -30f)
            quadToRelative(0f, -15f, 6f, -30f)
            reflectiveQuadToRelative(18f, -27f)
            lineToRelative(308f, -308f)
            quadToRelative(15f, -15f, 35.5f, -14.5f)
            reflectiveQuadTo(436f, 116f)
            quadToRelative(15f, 15f, 15f, 35f)
            reflectiveQuadToRelative(-15f, 35f)
            lineTo(142f, 480f)
            close()
        }
    }.build()
}