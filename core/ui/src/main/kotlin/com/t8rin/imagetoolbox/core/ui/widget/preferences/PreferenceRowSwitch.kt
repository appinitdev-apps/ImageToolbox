/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.preferences

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.icons.Check
import com.t8rin.imagetoolbox.core.ui.theme.blend
import com.t8rin.imagetoolbox.core.ui.utils.provider.SafeLocalContainerColor
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSwitch
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults

@Composable
fun PreferenceRowSwitch(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    subtitle: String? = null,
    autoShadowElevation: Dp = 1.dp,
    applyHorizontalPadding: Boolean = true,
    checked: Boolean,
    containerColor: Color = Color.Unspecified,
    contentColor: Color? = null,
    shape: Shape = ShapeDefaults.default,
    startContent: (@Composable () -> Unit)? = null,
    resultModifier: Modifier = Modifier.padding(
        horizontal = if (startContent != null) 0.dp else 16.dp,
        vertical = 8.dp
    ),
    drawStartIconContainer: Boolean = false,
    onDisabledClick: (() -> Unit)? = null,
    onClick: (Boolean) -> Unit,
    additionalContent: (@Composable () -> Unit)? = null,
    changeAlphaWhenDisabled: Boolean = true,
    drawContainer: Boolean = true,
    contentInsteadOfSwitch: (@Composable () -> Unit)? = null,
    titleFontStyle: TextStyle = PreferenceItemDefaults.TitleFontStyle,
) {
    val interactionSource = remember { MutableInteractionSource() }
    PreferenceRow(
        autoShadowElevation = autoShadowElevation,
        enabled = enabled,
        modifier = modifier,
        resultModifier = resultModifier,
        applyHorizontalPadding = applyHorizontalPadding,
        title = title,
        contentColor = contentColor,
        shape = shape,
        changeAlphaWhenDisabled = changeAlphaWhenDisabled,
        containerColor = containerColor,
        subtitle = subtitle,
        startContent = startContent,
        onDisabledClick = onDisabledClick,
        onClick = {
            if (contentInsteadOfSwitch == null) {
                onClick(!checked)
            }
        },
        drawStartIconContainer = drawStartIconContainer,
        endContent = {
            AnimatedContent(
                targetState = contentInsteadOfSwitch,
                modifier = Modifier.padding(start = 8.dp),
            ) { contentOfSwitch ->
                contentOfSwitch?.invoke() ?: EnhancedSwitch(
                    thumbIcon = if (checked) Icons.Rounded.Check else null,
                    colors = SwitchDefaults.colors(
                        uncheckedBorderColor = MaterialTheme.colorScheme.outline.blend(
                            containerColor, 0.3f
                        ),
                        uncheckedThumbColor = MaterialTheme.colorScheme.outline.blend(
                            containerColor, 0.3f
                        ),
                        uncheckedTrackColor = containerColor,
                        disabledUncheckedThumbColor = MaterialTheme.colorScheme.onSurface
                            .copy(alpha = 0.12f)
                            .compositeOver(MaterialTheme.colorScheme.surface),
                        checkedIconColor = MaterialTheme.colorScheme.primary
                    ),
                    enabled = enabled,
                    checked = checked,
                    onCheckedChange = onClick,
                    interactionSource = interactionSource,
                    colorUnderSwitch = containerColor.takeOrElse { SafeLocalContainerColor }
                )
            }
        },
        interactionSource = interactionSource,
        drawContainer = drawContainer,
        additionalContent = additionalContent,
        titleFontStyle = titleFontStyle
    )
}

@Composable
fun PreferenceRowSwitch(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    subtitle: String? = null,
    autoShadowElevation: Dp = 1.dp,
    checked: Boolean,
    containerColor: Color = Color.Unspecified,
    onDisabledClick: (() -> Unit)? = null,
    changeAlphaWhenDisabled: Boolean = true,
    contentColor: Color? = null,
    shape: Shape = ShapeDefaults.default,
    startIcon: ImageVector?,
    onClick: (Boolean) -> Unit,
    additionalContent: (@Composable () -> Unit)? = null,
    drawContainer: Boolean = true,
    resultModifier: Modifier = Modifier.padding(16.dp),
    contentInsteadOfSwitch: (@Composable () -> Unit)? = null,
    titleFontStyle: TextStyle = PreferenceItemDefaults.TitleFontStyle,
) {
    PreferenceRowSwitch(
        modifier = modifier,
        title = title,
        enabled = enabled,
        subtitle = subtitle,
        changeAlphaWhenDisabled = changeAlphaWhenDisabled,
        autoShadowElevation = autoShadowElevation,
        checked = checked,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape,
        onDisabledClick = onDisabledClick,
        drawStartIconContainer = true,
        startContent = startIcon?.let {
            {
                Icon(
                    imageVector = startIcon,
                    contentDescription = null
                )
            }
        },
        resultModifier = resultModifier,
        applyHorizontalPadding = false,
        onClick = onClick,
        additionalContent = additionalContent,
        drawContainer = drawContainer,
        contentInsteadOfSwitch = contentInsteadOfSwitch,
        titleFontStyle = titleFontStyle
    )
}