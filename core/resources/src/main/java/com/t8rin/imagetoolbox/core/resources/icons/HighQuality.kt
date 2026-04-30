/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.HighQuality: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.HighQuality",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(590f, 600f)
            verticalLineToRelative(30f)
            quadToRelative(0f, 13f, 8.5f, 21.5f)
            reflectiveQuadTo(620f, 660f)
            quadToRelative(13f, 0f, 21.5f, -8.5f)
            reflectiveQuadTo(650f, 630f)
            verticalLineToRelative(-30f)
            horizontalLineToRelative(30f)
            quadToRelative(17f, 0f, 28.5f, -11.5f)
            reflectiveQuadTo(720f, 560f)
            verticalLineToRelative(-160f)
            quadToRelative(0f, -17f, -11.5f, -28.5f)
            reflectiveQuadTo(680f, 360f)
            lineTo(560f, 360f)
            quadToRelative(-17f, 0f, -28.5f, 11.5f)
            reflectiveQuadTo(520f, 400f)
            verticalLineToRelative(160f)
            quadToRelative(0f, 17f, 11.5f, 28.5f)
            reflectiveQuadTo(560f, 600f)
            horizontalLineToRelative(30f)
            close()
            moveTo(300f, 520f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(50f)
            quadToRelative(0f, 13f, 8.5f, 21.5f)
            reflectiveQuadTo(410f, 600f)
            quadToRelative(13f, 0f, 21.5f, -8.5f)
            reflectiveQuadTo(440f, 570f)
            verticalLineToRelative(-180f)
            quadToRelative(0f, -13f, -8.5f, -21.5f)
            reflectiveQuadTo(410f, 360f)
            quadToRelative(-13f, 0f, -21.5f, 8.5f)
            reflectiveQuadTo(380f, 390f)
            verticalLineToRelative(70f)
            horizontalLineToRelative(-80f)
            verticalLineToRelative(-70f)
            quadToRelative(0f, -13f, -8.5f, -21.5f)
            reflectiveQuadTo(270f, 360f)
            quadToRelative(-13f, 0f, -21.5f, 8.5f)
            reflectiveQuadTo(240f, 390f)
            verticalLineToRelative(180f)
            quadToRelative(0f, 13f, 8.5f, 21.5f)
            reflectiveQuadTo(270f, 600f)
            quadToRelative(13f, 0f, 21.5f, -8.5f)
            reflectiveQuadTo(300f, 570f)
            verticalLineToRelative(-50f)
            close()
            moveTo(580f, 540f)
            verticalLineToRelative(-120f)
            horizontalLineToRelative(80f)
            verticalLineToRelative(120f)
            horizontalLineToRelative(-80f)
            close()
            moveTo(160f, 800f)
            quadToRelative(-33f, 0f, -56.5f, -23.5f)
            reflectiveQuadTo(80f, 720f)
            verticalLineToRelative(-480f)
            quadToRelative(0f, -33f, 23.5f, -56.5f)
            reflectiveQuadTo(160f, 160f)
            horizontalLineToRelative(640f)
            quadToRelative(33f, 0f, 56.5f, 23.5f)
            reflectiveQuadTo(880f, 240f)
            verticalLineToRelative(480f)
            quadToRelative(0f, 33f, -23.5f, 56.5f)
            reflectiveQuadTo(800f, 800f)
            lineTo(160f, 800f)
            close()
        }
    }.build()
}