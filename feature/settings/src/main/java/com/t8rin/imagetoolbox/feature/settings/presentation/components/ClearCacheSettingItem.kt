/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Delete
import com.t8rin.imagetoolbox.core.resources.icons.Memory
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun ClearCacheSettingItem(
    onClearCache: ((String) -> Unit) -> Unit,
    value: String,
    shape: Shape = ShapeDefaults.top,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    var cache by remember(value) {
        mutableStateOf(value)
    }

    PreferenceItem(
        shape = shape,
        onClick = {
            onClearCache { cache = it }
        },
        modifier = modifier,
        title = stringResource(R.string.cache_size),
        subtitle = stringResource(R.string.found_s, cache),
        endIcon = Icons.Outlined.Delete,
        startIcon = Icons.Outlined.Memory
    )
}