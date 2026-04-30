/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.graphics.get
import androidx.core.graphics.set
import com.t8rin.imagetoolbox.core.domain.model.Position
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.ensureActive

fun Canvas.drawBitmap(
    bitmap: Bitmap,
    position: Position,
    paint: Paint? = null,
    horizontalPadding: Float = 0f,
    verticalPadding: Float = 0f
) {
    val left = when (position) {
        Position.TopLeft, Position.CenterLeft, Position.BottomLeft -> horizontalPadding
        Position.TopCenter, Position.Center, Position.BottomCenter -> (width - bitmap.width) / 2f
        Position.TopRight, Position.CenterRight, Position.BottomRight -> (width - bitmap.width - horizontalPadding)
    }

    val top = when (position) {
        Position.TopLeft, Position.TopCenter, Position.TopRight -> verticalPadding
        Position.CenterLeft, Position.Center, Position.CenterRight -> (height - bitmap.height) / 2f
        Position.BottomLeft, Position.BottomCenter, Position.BottomRight -> (height - bitmap.height - verticalPadding)
    }

    drawBitmap(
        bitmap,
        null,
        RectF(
            left,
            top,
            bitmap.width + left,
            bitmap.height + top
        ),
        paint
    )
}

fun Canvas.drawBitmap(
    bitmap: Bitmap,
    left: Float = 0f,
    top: Float = 0f
) = drawBitmap(bitmap, left, top, Paint(Paint.ANTI_ALIAS_FLAG))

fun Canvas.drawBitmap(
    bitmap: Bitmap,
    left: Float = 0f,
    top: Float = 0f,
    paint: Paint
) = drawBitmap(bitmap, left, top, paint)

suspend fun Bitmap.healAlpha(
    original: Bitmap
): Bitmap = coroutineScope {
    val processed = this@healAlpha

    copy(Bitmap.Config.ARGB_8888, true).also { result ->
        for (y in 0 until original.height) {
            for (x in 0 until original.width) {
                ensureActive()
                val origPixel = original[x, y]
                val procPixel = processed[x, y]

                val origAlpha = origPixel ushr 24
                if (origAlpha >= 255) continue
                val newPixel = (origAlpha shl 24) or (procPixel and 0x00FFFFFF)

                result[x, y] = newPixel
            }
        }
    }
}