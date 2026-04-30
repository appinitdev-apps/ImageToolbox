/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.modifier

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager

fun Modifier.tappable(
    key1: Any? = Unit,
    onTap: PointerInputScope.(Offset) -> Unit
): Modifier = pointerInput(key1) {
    detectTapGestures { onTap(it) }
}

fun Modifier.clearFocusOnTap(enabled: Boolean = true) = composed {
    val focus = LocalFocusManager.current

    if (enabled) {
        Modifier.pointerInput(focus) {
            detectTapGestures(
                onTap = {
                    focus.clearFocus()
                }
            )
        }
    } else {
        Modifier
    }
}

@Composable
fun Disableable(
    enabled: Boolean,
    onDisabledClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .alpha(if (enabled) 1f else 0.5f)
    ) {
        content()
        if (!enabled) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .matchParentSize()
                    .tappable(onDisabledClick) {
                        onDisabledClick()
                    }
            ) {}
        }
    }
}