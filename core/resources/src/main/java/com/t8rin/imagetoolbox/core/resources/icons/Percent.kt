/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.Percent: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.Percent",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(300f, 440f)
            quadToRelative(-58f, 0f, -99f, -41f)
            reflectiveQuadToRelative(-41f, -99f)
            quadToRelative(0f, -58f, 41f, -99f)
            reflectiveQuadToRelative(99f, -41f)
            quadToRelative(58f, 0f, 99f, 41f)
            reflectiveQuadToRelative(41f, 99f)
            quadToRelative(0f, 58f, -41f, 99f)
            reflectiveQuadToRelative(-99f, 41f)
            close()
            moveTo(300f, 360f)
            quadToRelative(25f, 0f, 42.5f, -17.5f)
            reflectiveQuadTo(360f, 300f)
            quadToRelative(0f, -25f, -17.5f, -42.5f)
            reflectiveQuadTo(300f, 240f)
            quadToRelative(-25f, 0f, -42.5f, 17.5f)
            reflectiveQuadTo(240f, 300f)
            quadToRelative(0f, 25f, 17.5f, 42.5f)
            reflectiveQuadTo(300f, 360f)
            close()
            moveTo(660f, 800f)
            quadToRelative(-58f, 0f, -99f, -41f)
            reflectiveQuadToRelative(-41f, -99f)
            quadToRelative(0f, -58f, 41f, -99f)
            reflectiveQuadToRelative(99f, -41f)
            quadToRelative(58f, 0f, 99f, 41f)
            reflectiveQuadToRelative(41f, 99f)
            quadToRelative(0f, 58f, -41f, 99f)
            reflectiveQuadToRelative(-99f, 41f)
            close()
            moveTo(660f, 720f)
            quadToRelative(25f, 0f, 42.5f, -17.5f)
            reflectiveQuadTo(720f, 660f)
            quadToRelative(0f, -25f, -17.5f, -42.5f)
            reflectiveQuadTo(660f, 600f)
            quadToRelative(-25f, 0f, -42.5f, 17.5f)
            reflectiveQuadTo(600f, 660f)
            quadToRelative(0f, 25f, 17.5f, 42.5f)
            reflectiveQuadTo(660f, 720f)
            close()
            moveTo(188f, 772f)
            quadToRelative(-11f, -11f, -11f, -28f)
            reflectiveQuadToRelative(11f, -28f)
            lineToRelative(528f, -528f)
            quadToRelative(11f, -11f, 28f, -11f)
            reflectiveQuadToRelative(28f, 11f)
            quadToRelative(11f, 11f, 11f, 28f)
            reflectiveQuadToRelative(-11f, 28f)
            lineTo(244f, 772f)
            quadToRelative(-11f, 11f, -28f, 11f)
            reflectiveQuadToRelative(-28f, -11f)
            close()
        }
    }.build()
}