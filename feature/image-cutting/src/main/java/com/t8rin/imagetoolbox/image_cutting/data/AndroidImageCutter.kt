/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.image_cutting.data

import android.graphics.Bitmap
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import com.t8rin.imagetoolbox.core.data.image.utils.drawBitmap
import com.t8rin.imagetoolbox.core.data.utils.safeConfig
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ImageGetter
import com.t8rin.imagetoolbox.core.domain.utils.runSuspendCatching
import com.t8rin.imagetoolbox.image_cutting.domain.CutParams
import com.t8rin.imagetoolbox.image_cutting.domain.ImageCutter
import com.t8rin.imagetoolbox.image_cutting.domain.PivotPair
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.roundToInt

internal class AndroidImageCutter @Inject constructor(
    private val imageGetter: ImageGetter<Bitmap>,
    dispatchersHolder: DispatchersHolder
) : ImageCutter<Bitmap>, DispatchersHolder by dispatchersHolder {

    override suspend fun cutAndMerge(
        imageUri: String,
        params: CutParams
    ): Bitmap? {
        return cutAndMerge(
            image = imageGetter.getImage(
                data = imageUri,
                originalSize = true
            ) ?: return null,
            params = params
        )
    }

    override suspend fun cutAndMerge(
        image: Bitmap,
        params: CutParams
    ): Bitmap = withContext(defaultDispatcher) {
        runSuspendCatching {
            val (verticalStart, verticalEnd) = params.vertical.toCutBounds(image.width)
            val (horizontalStart, horizontalEnd) = params.horizontal.toCutBounds(image.height)

            image.cutAndMerge(
                verticalStart = verticalStart,
                verticalEnd = verticalEnd,
                horizontalStart = horizontalStart,
                horizontalEnd = horizontalEnd,
                inverseVertical = params.inverseVertical,
                inverseHorizontal = params.inverseHorizontal
            )
        }.getOrNull() ?: image
    }

    private fun PivotPair?.toCutBounds(
        size: Int
    ): Pair<Int?, Int?> {
        val bounds = this
            ?.takeIf { it != PivotPair(0f, 1f) }
            ?.let {
                (it.startRtlAdjusted * size).roundToInt() to
                        (it.endRtlAdjusted * size).roundToInt()
            }
            ?: return null to null

        val (start, end) = bounds

        return if (start in 0..size && end in 0..size && start < end) {
            start to end
        } else {
            null to null
        }
    }

    private suspend fun Bitmap.cutAndMerge(
        verticalStart: Int? = null,
        verticalEnd: Int? = null,
        horizontalStart: Int? = null,
        horizontalEnd: Int? = null,
        inverseVertical: Boolean = false,
        inverseHorizontal: Boolean = false
    ): Bitmap = coroutineScope {
        if (inverseVertical && inverseHorizontal) {
            Bitmap.createBitmap(
                this@cutAndMerge,
                verticalStart ?: 0,
                horizontalStart ?: 0,
                (verticalEnd ?: width) - (verticalStart ?: 0),
                (horizontalEnd ?: height) - (horizontalStart ?: 0)
            )
        } else {
            cutVertically(
                start = verticalStart,
                end = verticalEnd,
                inverse = inverseVertical
            ).cutHorizontally(
                start = horizontalStart,
                end = horizontalEnd,
                inverse = inverseHorizontal
            )
        }
    }

    private suspend fun Bitmap.cutHorizontally(
        start: Int?,
        end: Int?,
        inverse: Boolean
    ): Bitmap = coroutineScope {
        val source = this@cutHorizontally

        if (inverse) {
            if (start != null && end != null) {
                return@coroutineScope Bitmap.createBitmap(
                    source,
                    0,
                    start,
                    source.width,
                    end - start
                )
            } else if (start == null && end != null) {
                return@coroutineScope Bitmap.createBitmap(
                    source,
                    0,
                    0,
                    source.width,
                    end
                )
            } else if (start != null) {
                return@coroutineScope Bitmap.createBitmap(
                    source,
                    0,
                    start,
                    source.width,
                    source.height - start
                )
            }
        }


        val parts = mutableListOf<Bitmap>()
        if (start != null || end != null) {
            if (start != null && start > 0) {
                parts.add(
                    Bitmap.createBitmap(
                        source,
                        0,
                        0,
                        source.width,
                        start
                    )
                )
            }
            if (end != null && end < source.height) {
                parts.add(
                    Bitmap.createBitmap(
                        source,
                        0,
                        end,
                        source.width,
                        source.height - end
                    )
                )
            }
        } else {
            parts.add(source.copy(source.safeConfig, true))
        }

        val mergedWidth = parts.maxOf { it.width }
        val mergedHeight = parts.sumOf { it.height }

        createBitmap(mergedWidth, mergedHeight, source.safeConfig)
            .applyCanvas {
                var offsetY = 0f
                for (part in parts) {
                    drawBitmap(part, 0f, offsetY)
                    offsetY += part.height
                    part.recycle()
                }
            }
    }

    private suspend fun Bitmap.cutVertically(
        start: Int?,
        end: Int?,
        inverse: Boolean
    ): Bitmap = coroutineScope {
        val source = this@cutVertically

        if (inverse) {
            if (start != null && end != null) {
                return@coroutineScope Bitmap.createBitmap(
                    source,
                    start,
                    0,
                    end - start,
                    source.height
                )
            } else if (start == null && end != null) {
                return@coroutineScope Bitmap.createBitmap(
                    source,
                    0,
                    0,
                    end,
                    source.height
                )
            } else if (start != null) {
                return@coroutineScope Bitmap.createBitmap(
                    source,
                    start,
                    0,
                    source.width - start,
                    source.height
                )
            }
        }

        val parts = mutableListOf<Bitmap>()
        if (start != null || end != null) {
            if (start != null && start > 0) {
                parts.add(
                    Bitmap.createBitmap(
                        source,
                        0,
                        0,
                        start,
                        source.height
                    )
                )
            }
            if (end != null && end < source.width) {
                parts.add(
                    Bitmap.createBitmap(
                        source,
                        end,
                        0,
                        source.width - end,
                        source.height
                    )
                )
            }
        } else {
            parts.add(source.copy(source.safeConfig, true))
        }

        val mergedWidth = parts.sumOf { it.width }
        val mergedHeight = parts.maxOf { it.height }

        createBitmap(mergedWidth, mergedHeight, source.safeConfig)
            .applyCanvas {
                var offsetX = 0f
                for (part in parts) {
                    drawBitmap(part, offsetX, 0f)
                    offsetX += part.width
                    part.recycle()
                }
            }
    }

}