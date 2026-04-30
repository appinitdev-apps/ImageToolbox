/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.collages.utils

import android.graphics.PointF
import android.graphics.RectF
import com.t8rin.collages.view.PhotoItem

internal class ParamsManagerBuilder {
    private companion object {
        const val MIN_DIMENSION_TOLERANCE: Float = 1e-4f
    }

    private val paramValues: MutableList<Float> = mutableListOf()
    private val items: MutableList<PhotoItem> = mutableListOf()
    private val itemUpdates: MutableMap<Int, ItemUpdate> = mutableMapOf()
    private val itemHandles: MutableMap<Int, List<Handle>> = mutableMapOf()
    private val paramToItems: MutableMap<Int, MutableSet<Int>> = mutableMapOf()

    fun param(initial: Float): ParamT {
        paramValues.add(initial)
        return paramValues.lastIndex
    }

    fun addBoxedItem(
        photoItem: PhotoItem? = null,
        xParams: List<ParamT> = emptyList(),
        yParams: List<ParamT> = emptyList(),
        boxParams: (FloatArray) -> RectF,
        action: (PhotoItem, FloatArray, RectF) -> Unit = { _, _, _ -> }
    ): ParamsManagerBuilder {
        val photoItem = photoItem ?: PhotoItem()
        if (photoItem.pointList.isEmpty()) {
            photoItem.pointList.add(PointF(0f, 0f))
            photoItem.pointList.add(PointF(1f, 0f))
            photoItem.pointList.add(PointF(1f, 1f))
            photoItem.pointList.add(PointF(0f, 1f))
        }

        val handles = mutableListOf<Handle>()

        for (xParam in xParams) {
            handles.add(
                Handle.horizontal(
                    yProvider = {
                        boxParams(it).run { (bottom + top) / 2f }
                    },
                    managedParam = xParam
                )
            )
        }
        for (yParam in yParams) {
            handles.add(
                Handle.vertical(
                    xProvider = {
                        boxParams(it).run { (right + left) / 2f }
                    },
                    managedParam = yParam
                )
            )
        }

        return add(
            photoItem = photoItem,
            params = xParams + yParams,
            listener = { p, vs ->
                val box = boxParams(vs)
                if (box.left < 0) throw ParamsManager.InvalidValues()
                if (box.top < 0) throw ParamsManager.InvalidValues()
                if (box.right > 1f) throw ParamsManager.InvalidValues()
                if (box.bottom > 1f) throw ParamsManager.InvalidValues()
                if (box.right - box.left + MIN_DIMENSION_TOLERANCE < ParamsManager.minSize) {
                    throw ParamsManager.InvalidValues()
                }
                if (box.bottom - box.top + MIN_DIMENSION_TOLERANCE < ParamsManager.minSize) {
                    throw ParamsManager.InvalidValues()
                }
                p.bound.set(box)
                action(p, vs, box)
            },
            handles = handles
        )
    }

    fun add(
        photoItem: PhotoItem,
        params: List<ParamT> = emptyList(),
        listener: ItemUpdate = { _, _ -> },
        handles: List<Handle> = emptyList()
    ): ParamsManagerBuilder {
        photoItem.index = items.size
        items.add(photoItem)
        itemUpdates[photoItem.index] = listener
        itemHandles[photoItem.index] = handles
        for (p in params) paramToItems.getOrPut(p) { mutableSetOf() }.add(photoItem.index)
        return this
    }

    fun build(): Pair<ParamsManager, List<PhotoItem>> {
        val maxItemIndex = (items.maxOfOrNull { it.index } ?: -1) + 1
        val valuesArray = paramValues.toFloatArray()
        val itemUpdateArray = ArrayList<ItemUpdate?>(maxItemIndex).apply {
            repeat(maxItemIndex) { add(null) }
            for ((i, upd) in itemUpdates) if (i in 0 until maxItemIndex) this[i] = upd
        }
        val itemHandlesArray = ArrayList<List<Handle>>(maxItemIndex).apply {
            repeat(maxItemIndex) { add(emptyList()) }
            for ((i, hs) in itemHandles) if (i in 0 until maxItemIndex) this[i] = hs
        }
        val itemsByIndex = ArrayList<PhotoItem?>(maxItemIndex).apply {
            repeat(maxItemIndex) { add(null) }
            for (it in items) if (it.index in 0 until maxItemIndex) this[it.index] = it
        }
        val dependents: Array<IntArray> = Array(paramValues.size) { intArrayOf() }
        for ((p, set) in paramToItems) {
            dependents[p] = set.sorted().toIntArray()
        }

        val manager = ParamsManager(
            values = valuesArray,
            itemUpdateFunctions = itemUpdateArray,
            itemHandles = itemHandlesArray,
            itemsByIndex = itemsByIndex,
            paramToDependentItems = dependents
        )

        val values = manager.snapshotValues()

        // Apply initial updates
        for (photoItem in items) {
            val update = itemUpdateArray.getOrNull(photoItem.index)
            if (update != null) update(photoItem, values)
        }

        return manager to items.toList()
    }
}