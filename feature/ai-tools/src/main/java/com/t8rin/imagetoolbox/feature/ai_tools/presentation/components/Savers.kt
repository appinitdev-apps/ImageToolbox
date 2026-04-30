/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.presentation.components

import androidx.compose.runtime.saveable.listSaver
import com.t8rin.imagetoolbox.feature.ai_tools.domain.model.NeuralModel

internal val SpeedFiltersSaver = listSaver(
    save = { state ->
        state.map { speed ->
            NeuralModel.Speed.entries.indexOfFirst { it::class.isInstance(speed) }
        }
    },
    restore = { list ->
        list.map { NeuralModel.Speed.entries[it] }
    }
)

internal val TypeFiltersSaver = listSaver(
    save = { state -> state.map { it.name } },
    restore = { list ->
        list.map { NeuralModel.Type.valueOf(it) }
    }
)