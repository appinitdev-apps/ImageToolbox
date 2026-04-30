/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.color_tools.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gigamole.composefadingedges.FadingEdgesGravity
import com.t8rin.dynamic.theme.LocalDynamicThemeState
import com.t8rin.imagetoolbox.color_tools.presentation.components.ColorHarmonies
import com.t8rin.imagetoolbox.color_tools.presentation.components.ColorHistogram
import com.t8rin.imagetoolbox.color_tools.presentation.components.ColorInfo
import com.t8rin.imagetoolbox.color_tools.presentation.components.ColorMixing
import com.t8rin.imagetoolbox.color_tools.presentation.components.ColorShading
import com.t8rin.imagetoolbox.color_tools.presentation.screenLogic.ColorToolsComponent
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Palette
import com.t8rin.imagetoolbox.core.resources.icons.PushPin
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.settings.presentation.provider.rememberAppColorTuple
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.widget.AdaptiveLayoutScreen
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ColorRowSelector
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.modifier.fadingEdges
import com.t8rin.imagetoolbox.core.ui.widget.modifier.negativePadding
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee

@Composable
fun ColorToolsContent(
    component: ColorToolsComponent
) {
    val themeState = LocalDynamicThemeState.current
    val settingsState = LocalSettingsState.current
    val allowChangeColor = settingsState.allowChangeColorByImage

    val appColorTuple = rememberAppColorTuple()

    val selectedColor = component.selectedColor.takeOrElse { appColorTuple.primary }

    LaunchedEffect(selectedColor) {
        if (allowChangeColor) {
            themeState.updateColor(selectedColor)
        }
    }

    val isPortrait by isPortraitOrientationAsState()

    val isPinned = component.isPinned

    val colorSelector = @Composable {
        ColorRowSelector(
            value = selectedColor,
            onValueChange = component::updateSelectedColor,
            modifier = Modifier
                .fillMaxWidth()
                .container(
                    color = if (isPinned && !isPortrait) {
                        MaterialTheme.colorScheme.surface
                    } else {
                        Color.Unspecified
                    },
                    shape = ShapeDefaults.large
                ),
            icon = Icons.Rounded.Palette,
            title = stringResource(R.string.selected_color),
            topEndIcon = {
                EnhancedIconButton(
                    onClick = {
                        component.updateIsPinned(!isPinned)
                    }
                ) {
                    Icon(
                        imageVector = if (isPinned) Icons.Rounded.PushPin else Icons.Outlined.PushPin,
                        contentDescription = null
                    )
                }
            }
        )
    }

    key(isPinned) {
        AdaptiveLayoutScreen(
            title = {
                Text(
                    text = stringResource(R.string.color_tools),
                    modifier = Modifier.marquee()
                )
            },
            shouldDisableBackHandler = true,
            onGoBack = component.onGoBack,
            actions = {},
            topAppBarPersistentActions = {
                TopAppBarEmoji()
            },
            imagePreview = {
                Column(
                    modifier = if (isPortrait) {
                        Modifier
                            .negativePadding(horizontal = 20.dp)
                            .fadingEdges(
                                scrollableState = null,
                                isVertical = true,
                                length = 16.dp,
                                gravity = FadingEdgesGravity.End
                            )
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(20.dp)
                    } else {
                        Modifier
                    }
                ) {
                    colorSelector()
                }
            },
            useRegularStickyHeader = true,
            controls = { listState ->
                LaunchedEffect(isPinned) {
                    if (isPinned) {
                        listState.scrollToItem(0)
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (!isPinned) {
                        if (isPortrait) {
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        colorSelector()
                        Spacer(modifier = Modifier.fillMaxWidth())
                    }
                    ColorInfo(
                        selectedColor = selectedColor,
                        onColorChange = component::updateSelectedColor
                    )
                    ColorMixing(
                        selectedColor = selectedColor,
                        appColorTuple = appColorTuple
                    )
                    ColorHarmonies(
                        selectedColor = selectedColor
                    )
                    ColorShading(
                        selectedColor = selectedColor
                    )
                    ColorHistogram()
                }
            },
            buttons = {},
            placeImagePreview = isPinned,
            canShowScreenData = true
        )
    }
}