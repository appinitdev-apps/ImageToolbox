/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.palette_selection

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.t8rin.dynamic.theme.PaletteStyle
import com.t8rin.imagetoolbox.core.resources.icons.RadioButtonChecked
import com.t8rin.imagetoolbox.core.resources.icons.RadioButtonUnchecked
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.theme.takeColorFromScheme
import com.t8rin.imagetoolbox.core.ui.utils.provider.SafeLocalContainerColor
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem
import com.t8rin.imagetoolbox.core.utils.appContext

@Composable
fun PaletteStyleSelectionItem(
    style: PaletteStyle,
    onClick: () -> Unit
) {
    val settingsState = LocalSettingsState.current
    val selected = settingsState.themeStyle == style

    PreferenceItem(
        onClick = onClick,
        title = style.getTitle(appContext),
        subtitle = style.getSubtitle(appContext),
        containerColor = takeColorFromScheme {
            if (selected) secondaryContainer
            else SafeLocalContainerColor
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = settingsState.borderWidth,
                color = animateColorAsState(
                    if (selected) MaterialTheme.colorScheme
                        .onSecondaryContainer
                        .copy(alpha = 0.5f)
                    else Color.Transparent
                ).value,
                shape = ShapeDefaults.default
            ),
        endIcon = if (selected) Icons.Rounded.RadioButtonChecked else Icons.Rounded.RadioButtonUnchecked
    )
}