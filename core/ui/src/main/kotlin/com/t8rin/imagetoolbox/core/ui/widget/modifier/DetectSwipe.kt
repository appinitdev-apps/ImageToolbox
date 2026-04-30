/* #AppInitDev -> Photo Utility Hub */




package com.t8rin.imagetoolbox.core.ui.widget.modifier

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.onSwipeLeft(onSwipe: () -> Unit): Modifier {
    var dx = 0F

    return this.pointerInput(Unit) {
        detectHorizontalDragGestures(
            onDragEnd = {
                if (dx < 0) {
                    dx = 0F
                    onSwipe()
                }
            },
            onHorizontalDrag = { _, dragAmount ->
                dx = dragAmount
            }
        )
    }
}

fun Modifier.onSwipeRight(onSwipe: () -> Unit): Modifier {
    var dx = 0F

    return this.pointerInput(Unit) {
        detectHorizontalDragGestures(
            onDragEnd = {
                if (dx > 0) {
                    dx = 0F
                    onSwipe()
                }
            },
            onHorizontalDrag = { _, dragAmount ->
                dx = dragAmount
            }
        )
    }
}

fun Modifier.onSwipeDown(
    enabled: Boolean = true,
    onSwipe: () -> Unit
): Modifier {
    if (!enabled) return this

    var dy = 0F

    return this.pointerInput(Unit) {
        detectVerticalDragGestures(
            onDragEnd = {
                if (dy > 0) {
                    dy = 0F
                    onSwipe()
                }
            },
            onVerticalDrag = { _, dragAmount ->
                dy = dragAmount
            }
        )
    }
}

fun Modifier.detectSwipes(
    key: Any? = Unit,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
): Modifier {
    var dx = 0F

    return this.pointerInput(key) {
        detectHorizontalDragGestures(
            onDragEnd = {
                if (dx > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
                dx = 0F
            },
            onHorizontalDrag = { _, dragAmount ->
                dx = dragAmount
            }
        )
    }
}