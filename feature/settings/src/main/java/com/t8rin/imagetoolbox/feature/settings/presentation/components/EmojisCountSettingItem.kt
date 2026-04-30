/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.EmojiMultiple
import com.t8rin.imagetoolbox.core.resources.icons.Robot
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.hapticsClickable
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.utils.getString

@Composable
fun EmojisCountSettingItem(
    onValueChange: (Int) -> Unit,
    shape: Shape = ShapeDefaults.center,
    modifier: Modifier = Modifier
        .padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current

    EnhancedSliderItem(
        modifier = modifier.then(
            if (settingsState.selectedEmoji == null) {
                Modifier
                    .clip(ShapeDefaults.extraSmall)
                    .hapticsClickable {
                        AppToastHost.showToast(
                            message = getString(R.string.random_emojis_error),
                            icon = Icons.Rounded.Robot
                        )
                    }
            } else Modifier
        ),
        shape = shape,
        value = settingsState.emojisCount.coerceAtLeast(1),
        title = stringResource(R.string.emojis_count),
        icon = Icons.Outlined.EmojiMultiple,
        valueRange = 1f..5f,
        steps = 3,
        enabled = settingsState.selectedEmoji != null,
        onValueChange = {},
        internalStateTransformation = {
            it.toInt()
        },
        onValueChangeFinished = {
            onValueChange(it.toInt())
        }
    )
}