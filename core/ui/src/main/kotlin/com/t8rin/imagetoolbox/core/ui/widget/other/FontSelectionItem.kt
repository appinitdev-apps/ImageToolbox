/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.other

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.RadioButtonChecked
import com.t8rin.imagetoolbox.core.resources.icons.RadioButtonUnchecked
import com.t8rin.imagetoolbox.core.settings.presentation.model.UiFontFamily
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.theme.ProvideTypography
import com.t8rin.imagetoolbox.core.ui.theme.takeColorFromScheme
import com.t8rin.imagetoolbox.core.ui.utils.provider.SafeLocalContainerColor
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun FontSelectionItem(
    font: UiFontFamily,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null,
    shape: Shape = ShapeDefaults.default
) {
    val settingsState = LocalSettingsState.current
    val (_, name, isVariable) = font
    val selected = font == settingsState.font
    ProvideTypography(font) {
        PreferenceItem(
            onClick = onClick,
            onLongClick = onLongClick,
            title = (name ?: stringResource(id = R.string.system)) + isVariable.toVariable(),
            subtitle = stringResource(R.string.alphabet_and_numbers),
            containerColor = takeColorFromScheme {
                if (selected) secondaryContainer
                else SafeLocalContainerColor
            },
            modifier = modifier
                .border(
                    width = settingsState.borderWidth,
                    color = animateColorAsState(
                        if (selected) MaterialTheme
                            .colorScheme
                            .onSecondaryContainer
                            .copy(alpha = 0.5f)
                        else Color.Transparent
                    ).value,
                    shape = ShapeDefaults.default
                ),
            shape = shape,
            endIcon = if (selected) Icons.Rounded.RadioButtonChecked else Icons.Rounded.RadioButtonUnchecked
        )
    }
}

@Composable
private fun Boolean?.toVariable(): String {
    if (this == null || this) return ""
    return " (${stringResource(R.string.regular)})"
}