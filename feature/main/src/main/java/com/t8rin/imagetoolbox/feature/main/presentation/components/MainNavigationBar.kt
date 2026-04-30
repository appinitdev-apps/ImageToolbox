/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.main.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedNavigationBarItem
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.longPress
import com.t8rin.imagetoolbox.core.ui.widget.modifier.drawHorizontalStroke
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee

@Composable
internal fun MainNavigationBar(
    selectedIndex: Int,
    onValueChange: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.drawHorizontalStroke(top = true)
    ) {
        Screen.typedEntries.forEachIndexed { index, group ->
            val selected = index == selectedIndex
            val haptics = LocalHapticFeedback.current
            EnhancedNavigationBarItem(
                modifier = Modifier.weight(1f),
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
                    Text(
                        text = stringResource(group.title),
                        modifier = Modifier.marquee()
                    )
                }
            )
        }
    }
}