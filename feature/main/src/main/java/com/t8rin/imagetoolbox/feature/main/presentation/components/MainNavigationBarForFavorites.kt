/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.main.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Bookmark
import com.t8rin.imagetoolbox.core.resources.icons.ServiceToolbox
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedNavigationBarItem
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.longPress
import com.t8rin.imagetoolbox.core.ui.widget.modifier.drawHorizontalStroke
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee

@Composable
internal fun MainNavigationBarForFavorites(
    selectedIndex: Int,
    onValueChange: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.drawHorizontalStroke(top = true)
    ) {
        val haptics = LocalHapticFeedback.current

        EnhancedNavigationBarItem(
            modifier = Modifier.weight(1f),
            selected = selectedIndex == 0,
            onClick = {
                onValueChange(0)
                haptics.longPress()
            },
            icon = {
                AnimatedContent(
                    targetState = selectedIndex == 0,
                    transitionSpec = {
                        fadeIn() togetherWith fadeOut()
                    }
                ) { selected ->
                    Icon(
                        imageVector = if (selected) Icons.Rounded.Bookmark else Icons.Outlined.Bookmark,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text(
                    text = stringResource(R.string.favorite),
                    modifier = Modifier.marquee()
                )
            }
        )

        EnhancedNavigationBarItem(
            modifier = Modifier.weight(1f),
            selected = selectedIndex == 1,
            onClick = {
                onValueChange(1)
                haptics.longPress()
            },
            icon = {
                AnimatedContent(
                    targetState = selectedIndex == 1,
                    transitionSpec = {
                        fadeIn() togetherWith fadeOut()
                    }
                ) { selected ->
                    Icon(
                        imageVector = if (selected) Icons.Rounded.ServiceToolbox else Icons.Outlined.ServiceToolbox,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text(
                    text = stringResource(R.string.tools),
                    modifier = Modifier.marquee()
                )
            }
        )
    }
}