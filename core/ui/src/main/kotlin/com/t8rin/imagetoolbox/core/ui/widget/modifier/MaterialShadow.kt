/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.modifier

import android.os.Build
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.gigamole.composeshadowsplus.rsblur.rsBlurShadow
import com.t8rin.imagetoolbox.core.settings.domain.model.ShapeType
import com.zedalpha.shadowgadgets.compose.clippedShadow

fun Modifier.materialShadow(
    shape: Shape,
    elevation: Dp,
    enabled: Boolean = true,
    isClipped: Boolean = true,
    color: Color = Color.Black
) = composed {
    val elev = animateDpAsState(if (enabled) elevation else 0.dp).value

    if (elev > 0.dp) {
        val shape by remember(shape) {
            derivedStateOf {
                if ((shape is AnimatedShape && shape.shapesType is ShapeType.Smooth)) {
                    @Stable
                    object : Shape by shape {
                        override fun createOutline(
                            size: Size,
                            layoutDirection: LayoutDirection,
                            density: Density
                        ): Outline = shape.createOutline(
                            size = size,
                            layoutDirection = layoutDirection,
                            density = density,
                            shapesType = ShapeType.Rounded()
                        )
                    }
                } else shape
            }
        }
        val isConcavePath by remember(shape) {
            derivedStateOf {
                shape.createOutline(
                    size = Size(1f, 1f),
                    layoutDirection = LayoutDirection.Ltr,
                    density = Density(1f)
                ).let {
                    it is Outline.Generic && !it.path.isConvex
                }
            }
        }

        when {
            isConcavePath && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q -> {
                val api21Shadow = Modifier.rsBlurShadow(
                    shape = shape,
                    radius = elev,
                    isAlphaContentClip = isClipped,
                    color = color
                )

                api21Shadow
            }

            else -> {
                val api29Shadow = if (isClipped) {
                    Modifier.clippedShadow(
                        shape = shape,
                        elevation = elev,
                        ambientColor = color,
                        spotColor = color
                    )
                } else {
                    Modifier.shadow(
                        shape = shape,
                        elevation = elev,
                        ambientColor = color,
                        spotColor = color
                    )
                }

                api29Shadow
            }
        }
    } else Modifier
}