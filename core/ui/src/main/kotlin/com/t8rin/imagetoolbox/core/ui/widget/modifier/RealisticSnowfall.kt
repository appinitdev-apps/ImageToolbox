/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.modifier

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import com.t8rin.snowfall.snowfall
import com.t8rin.snowfall.types.FlakeType
import kotlin.random.Random

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.realisticSnowfall(
    enabled: Boolean = true
): Modifier = this.composed {
    if (enabled) {
        Modifier.snowfall(
            type = FlakeType.Custom(flakes),
            color = MaterialTheme.colorScheme.primary
        )
    } else Modifier
}

private val flakes = List(100) {
    val size = (40 * Random.nextDouble(0.3, 1.0)).toFloat()
    object : Painter() {
        override val intrinsicSize: Size = Size(size, size)

        override fun DrawScope.onDraw() {
            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        Color.White,
                        Color.Transparent,
                    )
                )
            )
        }
    }
}