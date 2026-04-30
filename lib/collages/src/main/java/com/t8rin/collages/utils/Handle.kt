/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.collages.utils

import android.graphics.PointF
import kotlin.math.atan2

internal interface Handle {
    fun getAngle(): Float
    fun draggablePoint(manager: ParamsManager): PointF
    fun tryDrag(point: PointF, manager: ParamsManager): PointF?

    companion object {
        fun horizontal(
            yProvider: (values: FloatArray) -> Float,
            managedParam: ParamT
        ): Handle = XHandle(
            yProvider = yProvider,
            managedParam = managedParam
        )

        fun vertical(
            xProvider: (values: FloatArray) -> Float,
            managedParam: ParamT
        ): Handle = YHandle(
            xProvider = xProvider,
            managedParam = managedParam
        )
    }
}

private abstract class LinearHandle(
    private val managedParam: ParamT,
    private val direction: PointF
) : Handle {
    override fun getAngle(): Float =
        Math.toDegrees(atan2(direction.y, direction.x).toDouble()).toFloat()

    protected abstract fun computeDraggablePoint(values: FloatArray): PointF
    protected abstract fun pointToValue(point: PointF): Float

    override fun draggablePoint(manager: ParamsManager): PointF =
        computeDraggablePoint(manager.valuesRef())

    override fun tryDrag(point: PointF, manager: ParamsManager): PointF? {
        val values = manager.valuesRef()
        val initialPoint = computeDraggablePoint(values)

        val dx = point.x - initialPoint.x
        val dy = point.y - initialPoint.y

        val norm = direction.x * dx + direction.y * dy
        val clippedPoint = PointF(
            initialPoint.x + direction.x * norm,
            initialPoint.y + direction.y * norm
        )

        val newValue = pointToValue(clippedPoint)

        return try {
            manager.updateParams(listOf(managedParam), floatArrayOf(newValue))
            clippedPoint
        } catch (e: ParamsManager.InvalidValues) {
            e.printStackTrace()
            null
        }
    }
}

private class XHandle(
    private val yProvider: (values: FloatArray) -> Float,
    private val managedParam: ParamT
) : LinearHandle(managedParam, PointF(1f, 0f)) {
    override fun computeDraggablePoint(values: FloatArray): PointF =
        PointF(values[managedParam], yProvider(values))

    override fun pointToValue(point: PointF): Float = point.x
}

private class YHandle(
    private val xProvider: (values: FloatArray) -> Float,
    private val managedParam: ParamT
) : LinearHandle(managedParam, PointF(0f, 1f)) {
    override fun computeDraggablePoint(values: FloatArray): PointF =
        PointF(xProvider(values), values[managedParam])

    override fun pointToValue(point: PointF): Float = point.y
}