/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.settings.domain.model

sealed interface ShapeType {
    val ordinal: Int get() = entries.indexOf(this)

    val strength: Float

    fun copy(strength: Float): ShapeType = when (this) {
        is Cut -> Cut(strength = strength)
        is Rounded -> Rounded(strength = strength)
        is Squircle -> Squircle(strength = strength)
        is Smooth -> Smooth(strength = strength)
    }

    class Rounded(
        override val strength: Float = 1f
    ) : ShapeType

    class Cut(
        override val strength: Float = 1f
    ) : ShapeType

    class Squircle(
        override val strength: Float = 1f
    ) : ShapeType

    class Smooth(
        override val strength: Float = 1f
    ) : ShapeType

    companion object {
        val entries by lazy {
            listOf(
                Rounded(), Cut(), Squircle(), Smooth()
            )
        }
    }
}