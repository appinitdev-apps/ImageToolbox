/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.presentation.components.model

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.CheckBoxOutlineBlank
import com.t8rin.imagetoolbox.core.resources.icons.Circle
import com.t8rin.imagetoolbox.core.resources.icons.FreeArrow
import com.t8rin.imagetoolbox.core.resources.icons.FreeDoubleArrow
import com.t8rin.imagetoolbox.core.resources.icons.Line
import com.t8rin.imagetoolbox.core.resources.icons.LineArrow
import com.t8rin.imagetoolbox.core.resources.icons.LineDoubleArrow
import com.t8rin.imagetoolbox.core.resources.icons.Polygon
import com.t8rin.imagetoolbox.core.resources.icons.Square
import com.t8rin.imagetoolbox.core.resources.icons.Star
import com.t8rin.imagetoolbox.core.resources.icons.Triangle
import com.t8rin.imagetoolbox.feature.markup_layers.domain.ShapeMode

internal val ShapeMode.Kind.titleRes: Int
    get() = when (this) {
        ShapeMode.Kind.Line -> R.string.line
        ShapeMode.Kind.Arrow -> R.string.arrow
        ShapeMode.Kind.DoubleArrow -> R.string.double_arrow
        ShapeMode.Kind.LineArrow -> R.string.line_arrow
        ShapeMode.Kind.DoubleLineArrow -> R.string.double_line_arrow
        ShapeMode.Kind.Rect -> R.string.rect
        ShapeMode.Kind.OutlinedRect -> R.string.outlined_rect
        ShapeMode.Kind.Oval -> R.string.oval
        ShapeMode.Kind.OutlinedOval -> R.string.outlined_oval
        ShapeMode.Kind.Triangle -> R.string.triangle
        ShapeMode.Kind.OutlinedTriangle -> R.string.outlined_triangle
        ShapeMode.Kind.Polygon -> R.string.polygon
        ShapeMode.Kind.OutlinedPolygon -> R.string.outlined_polygon
        ShapeMode.Kind.Star -> R.string.star
        ShapeMode.Kind.OutlinedStar -> R.string.outlined_star
    }

internal val ShapeMode.Kind.icon: ImageVector
    get() = when (this) {
        ShapeMode.Kind.Line -> Icons.Rounded.Line
        ShapeMode.Kind.Arrow -> Icons.Rounded.FreeArrow
        ShapeMode.Kind.DoubleArrow -> Icons.Rounded.FreeDoubleArrow
        ShapeMode.Kind.LineArrow -> Icons.Rounded.LineArrow
        ShapeMode.Kind.DoubleLineArrow -> Icons.Rounded.LineDoubleArrow
        ShapeMode.Kind.Rect -> Icons.Rounded.Square
        ShapeMode.Kind.OutlinedRect -> Icons.Rounded.CheckBoxOutlineBlank
        ShapeMode.Kind.Oval -> Icons.Rounded.Circle
        ShapeMode.Kind.OutlinedOval -> Icons.Outlined.Circle
        ShapeMode.Kind.Triangle -> Icons.Rounded.Triangle
        ShapeMode.Kind.OutlinedTriangle -> Icons.Outlined.Triangle
        ShapeMode.Kind.Polygon -> Icons.Rounded.Polygon
        ShapeMode.Kind.OutlinedPolygon -> Icons.Outlined.Polygon
        ShapeMode.Kind.Star -> Icons.Rounded.Star
        ShapeMode.Kind.OutlinedStar -> Icons.Outlined.Star
    }