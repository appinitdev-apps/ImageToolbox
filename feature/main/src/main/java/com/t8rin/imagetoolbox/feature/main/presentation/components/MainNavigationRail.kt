/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.main.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.theme.outlineVariant
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedNavigationRailItem
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.enhancedVerticalScroll
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.longPress
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container

@Composable
internal fun MainNavigationRail(
    selectedIndex: Int,
    onValueChange: (Int) -> Unit
) {
    val settingsState = LocalSettingsState.current

    Row {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(min = 80.dp)
                .container(
                    shape = RectangleShape,
                    autoShadowElevation = 10.dp,
                    resultPadding = 0.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
                    .enhancedVerticalScroll(rememberScrollState())
                    .navigationBarsPadding()
                    .padding(
                        start = WindowInsets
                            .statusBars
                            .asPaddingValues()
                            .calculateStartPadding(LocalLayoutDirection.current)
                    )
                    .padding(
                        start = WindowInsets
                            .displayCutout
                            .asPaddingValues()
                            .calculateStartPadding(LocalLayoutDirection.current)
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    4.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(8.dp))
                Screen.typedEntries.forEachIndexed { index, group ->
                    val selected = index == selectedIndex
                    val haptics = LocalHapticFeedback.current
                    EnhancedNavigationRailItem(
                        modifier = Modifier.width(100.dp),
                        selected = selected,
                        onClick = {
                            onValueChange(index)
                            haptics.longPress()
                        },
                        icon = {
                            AnimatedContent(
                                targetState = selected,
                                transitionSpec = {
                                    fadeIn() togetherWith fadeOut()
                                }
                            ) { selected ->
                                Icon(
                                    imageVector = group.icon(selected),
                                    contentDescription = stringResource(group.title)
                                )
                            }
                        },
                        label = {
                            Text(stringResource(group.title))
                        }
                    )
                }
                Spacer(Modifier.height(8.dp))
            }
        }
        Spacer(
            Modifier
                .fillMaxHeight()
                .width(settingsState.borderWidth)
                .background(
                    MaterialTheme.colorScheme.outlineVariant(
                        0.3f,
                        DrawerDefaults.standardContainerColor
                    )
                )
        )
    }
}