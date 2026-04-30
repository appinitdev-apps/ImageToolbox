/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.draw.presentation.components.element

import androidx.compose.foundation.layout.fillMaxWidth
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.FormatColorFill
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ColorRowSelector
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container

@Composable
fun OutlinedFillColorSelector(
    value: Color?,
    onValueChange: (Color?) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = ShapeDefaults.default,
    containerColor: Color = Color.Unspecified
) {
    ColorRowSelector(
        value = value,
        onValueChange = onValueChange,
        onNullClick = { onValueChange(null) },
        title = stringResource(R.string.fill_color),
        icon = Icons.Rounded.FormatColorFill,
        allowAlpha = true,
        modifier = modifier
            .fillMaxWidth()
            .container(
                color = containerColor,
                shape = shape
            )
    )
}