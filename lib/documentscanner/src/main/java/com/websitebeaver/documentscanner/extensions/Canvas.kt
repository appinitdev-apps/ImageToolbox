/* #AppInitDev -> Photo Utility Hub */



package com.websitebeaver.documentscanner.extensions

import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.graphics.drawable.Drawable
import com.websitebeaver.documentscanner.enums.QuadCorner
import com.websitebeaver.documentscanner.models.Line
import com.websitebeaver.documentscanner.models.Quad

/**
 * This draws a quad (used to draw cropper). It draws 4 circles and
 * 4 connecting lines
 *
 * @param quad 4 corners
 * @param pointRadius corner circle radius
 * @param cropperLineStyle quad line style (color, thickness for example)
 * @param cropperCornerStyle quad corner style (color, thickness for example)
 * @param cropperCornerOutlineStyle quad corner outer outline style
 * @param cropperSelectedCornerFillStyles style for selected corner
 * @param selectedCornerProgress corner selection animation progress
 */
fun Canvas.drawQuad(
    quad: Quad,
    pointRadius: Float,
    cropperLineStyle: Paint,
    cropperCornerStyle: Paint,
    cropperCornerOutlineStyle: Paint,
    cropperSelectedCornerFillStyles: Paint,
    selectedCornerProgress: Map<QuadCorner, Float>,
    imagePreviewBounds: RectF,
    ratio: Float,
    selectedCornerRadiusMagnification: Float,
    selectedCornerBackgroundMagnification: Float,
) {
    fun drawEdges() {
        for (edge: Line in quad.edges) {
            drawLine(edge.from.x, edge.from.y, edge.to.x, edge.to.y, cropperLineStyle)
        }
    }

    fun Float.lerpTo(target: Float, progress: Float): Float = this + (target - this) * progress

    // draw connecting lines under the corner handles
    drawEdges()

    // draw selected corner magnifier fills first, then redraw edges so the frame is visible in zoom
    for ((quadCorner: QuadCorner, cornerPoint: PointF) in quad.corners) {
        val progress = selectedCornerProgress[quadCorner]?.coerceIn(0f, 1f) ?: 0f
        if (progress <= 0f) continue

        val circleRadius = pointRadius.lerpTo(
            selectedCornerRadiusMagnification * pointRadius,
            progress
        )
        val backgroundMagnification = 1f.lerpTo(
            selectedCornerBackgroundMagnification,
            progress
        )
        val matrix = Matrix()
        matrix.postScale(ratio, ratio, ratio / cornerPoint.x, ratio / cornerPoint.y)
        matrix.postTranslate(imagePreviewBounds.left, imagePreviewBounds.top)
        matrix.postScale(
            backgroundMagnification,
            backgroundMagnification,
            cornerPoint.x,
            cornerPoint.y
        )
        cropperSelectedCornerFillStyles.shader.setLocalMatrix(matrix)
        // fill selected corner circle with magnified image, so it's easier to crop
        drawCircle(cornerPoint.x, cornerPoint.y, circleRadius, cropperSelectedCornerFillStyles)
    }

    if (selectedCornerProgress.isNotEmpty()) {
        drawEdges()
    }

    // draw 4 corner points
    for ((quadCorner: QuadCorner, cornerPoint: PointF) in quad.corners) {
        val progress = selectedCornerProgress[quadCorner]?.coerceIn(0f, 1f) ?: 0f
        val circleRadius = pointRadius.lerpTo(
            selectedCornerRadiusMagnification * pointRadius,
            progress
        )

        // draw outer ring around corner circles
        drawCircle(
            cornerPoint.x,
            cornerPoint.y,
            circleRadius + cropperCornerOutlineStyle.strokeWidth,
            cropperCornerOutlineStyle
        )

        // draw corner circles
        drawCircle(
            cornerPoint.x,
            cornerPoint.y,
            circleRadius,
            cropperCornerStyle
        )
    }
}

/**
 * This draws the check icon on the finish document scan button. It's needed
 * because the inner circle covers the check icon.
 *
 * @param buttonCenterX the button center x coordinate
 * @param buttonCenterY the button center y coordinate
 * @param drawable the check icon
 */
fun Canvas.drawCheck(
    buttonCenterX: Float,
    buttonCenterY: Float,
    drawable: Drawable,
    tintColor: Int? = null
) {
    val mutate = drawable.constantState?.newDrawable()?.mutate()
    tintColor?.let { mutate?.setTint(it) }
    mutate?.setBounds(
        (buttonCenterX - drawable.intrinsicWidth.toFloat() / 2).toInt(),
        (buttonCenterY - drawable.intrinsicHeight.toFloat() / 2).toInt(),
        (buttonCenterX + drawable.intrinsicWidth.toFloat() / 2).toInt(),
        (buttonCenterY + drawable.intrinsicHeight.toFloat() / 2).toInt()
    )
    mutate?.draw(this)
}