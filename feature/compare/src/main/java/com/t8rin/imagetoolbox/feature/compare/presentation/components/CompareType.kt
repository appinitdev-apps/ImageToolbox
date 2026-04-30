/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.compare.presentation.components

import androidx.annotation.StringRes
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Compare
import com.t8rin.imagetoolbox.core.resources.icons.Cube
import com.t8rin.imagetoolbox.core.resources.icons.Tonality
import com.t8rin.imagetoolbox.core.resources.icons.TouchApp
import com.t8rin.imagetoolbox.core.resources.icons.ZoomIn

sealed class CompareType(
    val icon: ImageVector,
    @StringRes val title: Int
) {
    data object Slide : CompareType(
        icon = Icons.Outlined.Compare,
        title = R.string.slide
    )

    data object SideBySide : CompareType(
        icon = Icons.Outlined.ZoomIn,
        title = R.string.side_by_side
    )

    data object Tap : CompareType(
        icon = Icons.Outlined.TouchApp,
        title = R.string.toggle_tap
    )

    data object Transparency : CompareType(
        icon = Icons.Outlined.Tonality,
        title = R.string.transparency
    )

    data object PixelByPixel : CompareType(
        icon = Icons.Outlined.Cube,
        title = R.string.pixel_by_pixel
    )

    companion object {
        val entries by lazy {
            listOf(Slide, SideBySide, Tap, Transparency, PixelByPixel)
        }
    }
}