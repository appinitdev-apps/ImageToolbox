/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.other

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBorder(
    modifier: Modifier,
    alpha: Float,
    scale: Float,
    shape: Shape
) {
    val pathEffect = rememberAnimatedBorder()

    val density = LocalDensity.current
    val colorScheme = MaterialTheme.colorScheme

    Canvas(modifier = modifier) {
        val outline = shape.createOutline(
            size = size,
            layoutDirection = layoutDirection,
            density = density
        )
        drawOutline(
            outline = outline,
            color = colorScheme.primary.copy(alpha),
            style = Stroke(
                width = 3.dp.toPx() * (1f / scale)
            )
        )
        drawOutline(
            outline = outline,
            color = colorScheme.primaryContainer.copy(alpha),
            style = Stroke(
                width = 3.dp.toPx() * (1f / scale),
                pathEffect = pathEffect
            )
        )
    }
}

@Composable
fun rememberAnimatedBorder(
    intervals: FloatArray = floatArrayOf(20f, 20f),
    phase: Float = 80f,
    repeatDuration: Int = 1000
): PathEffect = PathEffect.dashPathEffect(
    intervals = intervals,
    phase = rememberAnimatedBorderPhase(
        phase = phase,
        repeatDuration = repeatDuration
    )
)

@Composable
fun rememberAnimatedBorderPhase(
    phase: Float = 80f,
    repeatDuration: Int = 1000
): Float {
    val transition = rememberInfiniteTransition()

    val animatedPhase by transition.animateFloat(
        initialValue = 0f,
        targetValue = phase,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = repeatDuration,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    return animatedPhase
}