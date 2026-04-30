/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.image_stitch.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButtonGroup
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.feature.image_stitch.domain.StitchFadeSide

@Composable
fun ImageFadingEdgesSelector(
    modifier: Modifier = Modifier,
    value: StitchFadeSide,
    onValueChange: (StitchFadeSide) -> Unit
) {
    EnhancedButtonGroup(
        modifier = modifier
            .container(shape = ShapeDefaults.extraLarge),
        title = stringResource(id = R.string.fading_edges),
        entries = StitchFadeSide.entries,
        value = value,
        onValueChange = onValueChange,
        itemContent = {
            Text(it.title())
        }
    )
}

@Composable
private fun StitchFadeSide.title() = when (this) {
    StitchFadeSide.None -> stringResource(R.string.disabled)
    StitchFadeSide.Start -> stringResource(R.string.start)
    StitchFadeSide.End -> stringResource(R.string.end)
    StitchFadeSide.Both -> stringResource(R.string.both)
}