/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.other

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.modifier.scaleOnTap

@Composable
fun TopAppBarEmoji() {
   /* val settingsState = LocalSettingsState.current

    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .scaleOnTap {
                AppToastHost.showConfetti()
            }
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            repeat(5) {
                AnimatedVisibility(
                    visible = settingsState.emojisCount > it,
                    enter = fadeIn() + slideInHorizontally(),
                    exit = fadeOut() + slideOutHorizontally()
                ) {
                    EmojiItem(
                        fontScale = LocalSettingsState.current.fontScale
                            ?: LocalDensity.current.fontScale,
                        emoji = settingsState.selectedEmoji?.toString(),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize
                    )
                }
            }
        }
    }*/
}