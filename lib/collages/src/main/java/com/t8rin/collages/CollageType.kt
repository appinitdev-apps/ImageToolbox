/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.collages

import com.t8rin.collages.model.CollageLayout

@ConsistentCopyVisibility
data class CollageType internal constructor(
    internal val layout: CollageLayout?,
    internal val index: Int?
) {
    companion object {
        val Empty by lazy {
            CollageType(
                layout = null,
                index = null
            )
        }
    }
}