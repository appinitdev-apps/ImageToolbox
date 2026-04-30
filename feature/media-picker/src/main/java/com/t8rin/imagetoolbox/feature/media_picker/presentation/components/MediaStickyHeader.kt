/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.media_picker.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.widget.buttons.MediaCheckBox
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.hapticsCombinedClickable

@Composable
fun MediaStickyHeader(
    modifier: Modifier = Modifier,
    date: String,
    isCheckVisible: MutableState<Boolean>,
    isChecked: Boolean,
    onChecked: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 24.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.hapticsCombinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onLongClick = onChecked,
                onClick = {
                    if (isCheckVisible.value) onChecked?.invoke()
                }
            )
        )
        if (onChecked != null) {
            AnimatedVisibility(
                visible = isCheckVisible.value,
                enter = enterAnimation,
                exit = exitAnimation
            ) {
                MediaCheckBox(
                    isChecked = isChecked,
                    onCheck = onChecked,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

private val enterAnimation = fadeIn(tween(150))
private val exitAnimation = fadeOut(tween(150))