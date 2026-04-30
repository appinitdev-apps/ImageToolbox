/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.animation

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

@Stable
@Immutable
class CombinedMutableInteractionSource(
    private val sources: List<MutableInteractionSource>
) : MutableInteractionSource {

    constructor(vararg sources: MutableInteractionSource) : this(sources.toList())

    override val interactions: Flow<Interaction> =
        merge(*sources.map { it.interactions }.toTypedArray())

    override suspend fun emit(interaction: Interaction) {
        sources.forEach { it.emit(interaction) }
    }

    override fun tryEmit(interaction: Interaction): Boolean {
        return sources.all { it.tryEmit(interaction) }
    }

}