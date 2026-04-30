/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.resources.icons

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Rounded.GridOn: ImageVector by lazy(LazyThreadSafetyMode.NONE) {
    ImageVector.Builder(
        name = "Rounded.GridOn",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(fill = SolidColor(Color.Black)) {
            moveTo(200f, 840f)
            horizontalLineToRelative(107f)
            verticalLineToRelative(-187f)
            lineTo(120f, 653f)
            verticalLineToRelative(107f)
            quadToRelative(0f, 33f, 23.5f, 56.5f)
            reflectiveQuadTo(200f, 840f)
            close()
            moveTo(387f, 840f)
            horizontalLineToRelative(186f)
            verticalLineToRelative(-187f)
            lineTo(387f, 653f)
            verticalLineToRelative(187f)
            close()
            moveTo(653f, 840f)
            horizontalLineToRelative(107f)
            quadToRelative(33f, 0f, 56.5f, -23.5f)
            reflectiveQuadTo(840f, 760f)
            verticalLineToRelative(-107f)
            lineTo(653f, 653f)
            verticalLineToRelative(187f)
            close()
            moveTo(120f, 573f)
            horizontalLineToRelative(187f)
            verticalLineToRelative(-186f)
            lineTo(120f, 387f)
            verticalLineToRelative(186f)
            close()
            moveTo(387f, 573f)
            horizontalLineToRelative(186f)
            verticalLineToRelative(-186f)
            lineTo(387f, 387f)
            verticalLineToRelative(186f)
            close()
            moveTo(653f, 573f)
            horizontalLineToRelative(187f)
            verticalLineToRelative(-186f)
            lineTo(653f, 387f)
            verticalLineToRelative(186f)
            close()
            moveTo(120f, 307f)
            horizontalLineToRelative(187f)
            verticalLineToRelative(-187f)
            lineTo(200f, 120f)
            quadToRelative(-33f, 0f, -56.5f, 23.5f)
            reflectiveQuadTo(120f, 200f)
            verticalLineToRelative(107f)
            close()
            moveTo(387f, 307f)
            horizontalLineToRelative(186f)
            verticalLineToRelative(-187f)
            lineTo(387f, 120f)
            verticalLineToRelative(187f)
            close()
            moveTo(653f, 307f)
            horizontalLineToRelative(187f)
            verticalLineToRelative(-107f)
            quadToRelative(0f, -33f, -23.5f, -56.5f)
            reflectiveQuadTo(760f, 120f)
            lineTo(653f, 120f)
            verticalLineToRelative(187f)
            close()
        }
    }.build()
}