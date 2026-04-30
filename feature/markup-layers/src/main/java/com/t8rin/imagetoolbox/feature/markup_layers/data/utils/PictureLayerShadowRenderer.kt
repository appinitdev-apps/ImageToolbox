/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.data.utils

import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.RectF
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import androidx.core.graphics.withSave
import com.t8rin.imagetoolbox.feature.markup_layers.domain.DropShadow
import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.math.roundToInt

internal data class PictureShadowRenderData(
    val bitmap: Bitmap,
    val left: Float,
    val top: Float,
    val rasterScale: Float
)

internal fun resolveLayerShadowRasterScale(
    layerScale: Float
): Float = layerScale
    .absoluteValue
    .coerceAtLeast(1f)
    .coerceAtMost(MAX_SHADOW_RASTER_SCALE)

internal fun buildPictureShadowRenderData(
    sourceBitmap: Bitmap,
    shadow: DropShadow?,
    targetWidth: Float,
    targetHeight: Float,
    cornerRadiusPercent: Int = 0,
    rasterScale: Float = 1f
): PictureShadowRenderData? {
    shadow ?: return null

    val safeRasterScale = rasterScale.coerceAtLeast(1f)
    val safeTargetWidth = (targetWidth
        .coerceAtLeast(1f)
            * safeRasterScale)
        .roundToInt()
        .coerceAtLeast(1)
    val safeTargetHeight = (targetHeight
        .coerceAtLeast(1f)
            * safeRasterScale)
        .roundToInt()
        .coerceAtLeast(1)
    val targetRect = RectF(0f, 0f, safeTargetWidth.toFloat(), safeTargetHeight.toFloat())
    val cornerRadiusPx = calculatePictureCornerRadiusPx(
        cornerRadiusPercent = cornerRadiusPercent,
        width = targetRect.width(),
        height = targetRect.height()
    )

    val contentBitmap = createBitmap(
        width = safeTargetWidth,
        height = safeTargetHeight
    ).applyCanvas {
        withSave {
            if (cornerRadiusPx > 0f) {
                clipPath(
                    Path().apply {
                        addRoundRect(
                            targetRect,
                            cornerRadiusPx,
                            cornerRadiusPx,
                            Path.Direction.CW
                        )
                    }
                )
            }
            drawBitmap(
                sourceBitmap,
                null,
                targetRect,
                Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    isFilterBitmap = true
                }
            )
        }
    }

    val blurRadius = shadow.blurRadius.coerceAtLeast(0f) * safeRasterScale
    val offset = IntArray(2)
    val alphaBitmap = contentBitmap.extractAlpha(
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            if (blurRadius > 0f) {
                maskFilter = BlurMaskFilter(
                    blurRadius,
                    BlurMaskFilter.Blur.NORMAL
                )
            }
        },
        offset
    )
    val tintedBitmap = createBitmap(
        width = alphaBitmap.width.coerceAtLeast(1),
        height = alphaBitmap.height.coerceAtLeast(1)
    ).applyCanvas {
        drawBitmap(
            alphaBitmap,
            0f,
            0f,
            Paint(Paint.ANTI_ALIAS_FLAG).apply {
                isFilterBitmap = true
            }
        )
        drawColor(shadow.color, PorterDuff.Mode.SRC_IN)
    }

    contentBitmap.recycle()
    alphaBitmap.recycle()

    return PictureShadowRenderData(
        bitmap = tintedBitmap,
        left = offset[0].toFloat() + shadow.offsetX * safeRasterScale,
        top = offset[1].toFloat() + shadow.offsetY * safeRasterScale,
        rasterScale = safeRasterScale
    )
}

private const val MAX_SHADOW_RASTER_SCALE = 4f

private fun calculatePictureCornerRadiusPx(
    cornerRadiusPercent: Int,
    width: Float,
    height: Float
): Float {
    val normalizedPercent = cornerRadiusPercent.coerceIn(0, 50)
    if (normalizedPercent == 0) return 0f

    return min(width, height) * (normalizedPercent / 100f)
}