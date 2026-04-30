/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.selection

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.domain.image.model.BlendingMode
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Layers
import com.t8rin.imagetoolbox.core.ui.utils.helper.entries
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults

@Composable
fun BlendingModeSelector(
    value: BlendingMode,
    onValueChange: (BlendingMode) -> Unit,
    entries: List<BlendingMode> = remember {
        mutableListOf<BlendingMode>().apply {
            add(BlendingMode.SrcOver)
            addAll(
                BlendingMode
                    .entries
                    .toList() - listOf(
                    BlendingMode.SrcOver,
                    BlendingMode.Clear,
                    BlendingMode.Src,
                    BlendingMode.Dst
                ).toSet()
            )
        }
    },
    modifier: Modifier = Modifier,
    shape: Shape = ShapeDefaults.large,
    color: Color = MaterialTheme.colorScheme.surface
) {
    DataSelector(
        value = value,
        onValueChange = onValueChange,
        entries = entries,
        title = stringResource(R.string.overlay_mode),
        titleIcon = Icons.Outlined.Layers,
        itemContentText = { it.toString() },
        modifier = modifier,
        shape = shape,
        containerColor = color
    )
}