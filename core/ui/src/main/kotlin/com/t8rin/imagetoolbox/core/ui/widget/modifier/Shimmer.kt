/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.modifier

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.theme.harmonizeWithPrimary

fun Modifier.shimmer(
    visible: Boolean,
    color: Color = Color.Unspecified
) = this.composed {
    Modifier.placeholder(
        visible = visible,
        color = color.takeOrElse {
            MaterialTheme.colorScheme.surfaceColorAtElevation(16.dp)
        },
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = color.harmonizeWithPrimary(0.5f)
        )
    )
}