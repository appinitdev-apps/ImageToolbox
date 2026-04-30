/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.domain

import com.t8rin.imagetoolbox.core.domain.image.model.BlendingMode
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.model.Outline
import com.t8rin.imagetoolbox.core.settings.domain.model.FontType

data class MarkupLayer(
    val type: LayerType,
    val position: LayerPosition,
    val contentSize: IntegerSize = IntegerSize.Zero,
    val visibleLineCount: Int? = null,
    val cornerRadiusPercent: Int = 0,
    val isLocked: Boolean = false,
    val blendingMode: BlendingMode = BlendingMode.SrcOver,
    val groupedLayers: List<MarkupLayer> = emptyList()
)

data class LayerPosition(
    val scale: Float = 1f,
    val rotation: Float = 0f,
    val isFlippedHorizontally: Boolean = false,
    val isFlippedVertically: Boolean = false,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val alpha: Float = 1f,
    val currentCanvasSize: IntegerSize,
    val coerceToBounds: Boolean,
    val isVisible: Boolean
)

typealias DomainTextDecoration = LayerType.Text.Decoration

data class TextGeometricTransform(
    val scaleX: Float = 1f,
    val skewX: Float = 0f
)

data class DropShadow(
    val color: Int = 0xFF000000.toInt(),
    val offsetX: Float = 0f,
    val offsetY: Float = 6f,
    val blurRadius: Float = 12f
) {
    companion object {
        val Default = DropShadow()

        val BlurRadiusRange: ClosedFloatingPointRange<Float>
            get() = 0f..100f

        val OffsetXRange: ClosedFloatingPointRange<Float>
            get() = -64f..64f

        val OffsetYRange: ClosedFloatingPointRange<Float>
            get() = -64f..64f
    }
}

sealed interface LayerType {
    data class Text(
        val color: Int,
        val size: Float,
        val font: FontType?,
        val backgroundColor: Int,
        val text: String,
        val decorations: List<Decoration>,
        val outline: Outline?,
        val alignment: Alignment,
        val geometricTransform: TextGeometricTransform? = null,
        val shadow: DropShadow? = null
    ) : LayerType {

        enum class Decoration {
            Bold, Italic, Underline, LineThrough
        }

        enum class Alignment {
            Start, Center, End
        }

        companion object {
            val Default by lazy {
                Text(
                    color = -16777216,
                    size = 0.5f,
                    font = null,
                    backgroundColor = 0,
                    text = "Text",
                    decorations = listOf(),
                    outline = null,
                    alignment = Alignment.Start,
                    geometricTransform = null,
                    shadow = null
                )
            }
        }
    }

    sealed class Picture(
        open val imageData: Any,
        open val shadow: DropShadow? = null
    ) : LayerType {
        data class Image(
            override val imageData: Any,
            override val shadow: DropShadow? = null
        ) : Picture(
            imageData = imageData,
            shadow = shadow
        )

        data class Sticker(
            override val imageData: Any,
            override val shadow: DropShadow? = null
        ) : Picture(
            imageData = imageData,
            shadow = shadow
        )
    }

    data class Shape(
        val shapeMode: ShapeMode,
        val color: Int,
        val strokeWidth: Float = 16f,
        val widthRatio: Float = 0.35f,
        val heightRatio: Float = 0.35f,
        val shadow: DropShadow? = null
    ) : LayerType {

        companion object {
            val Default by lazy {
                Shape(
                    shapeMode = ShapeMode.Star(),
                    color = -16777216,
                    strokeWidth = 16f,
                    widthRatio = 0.35f,
                    heightRatio = 0.35f,
                    shadow = null
                )
            }
        }
    }
}

internal fun LayerType.layerCornerRadiusPercent(value: Int): Int = when (this) {
    is LayerType.Shape -> 0
    else -> value.coerceIn(0, 50)
}