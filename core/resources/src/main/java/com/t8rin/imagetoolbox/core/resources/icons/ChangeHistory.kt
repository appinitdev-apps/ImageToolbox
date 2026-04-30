/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Outlined.ChangeHistory: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Outlined.ChangeHistory",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(152f, 800f)
            quadToRelative(-23f, 0f, -35f, -20.5f)
            reflectiveQuadToRelative(1f, -40.5f)
            lineToRelative(328f, -525f)
            quadToRelative(12f, -19f, 34f, -19f)
            reflectiveQuadToRelative(34f, 19f)
            lineToRelative(328f, 525f)
            quadToRelative(13f, 20f, 1f, 40.5f)
            reflectiveQuadTo(808f, 800f)
            lineTo(152f, 800f)
            close()
            moveTo(224f, 720f)
            horizontalLineToRelative(512f)
            lineTo(480f, 310f)
            lineTo(224f, 720f)
            close()
            moveTo(480f, 515f)
            close()
        }
    }.build()
}