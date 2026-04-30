/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import com.t8rin.imagetoolbox.app.presentation.components.ImageToolboxApplication
import com.t8rin.imagetoolbox.core.domain.HF_BASE_URL
import com.t8rin.neural_tools.NeuralTool

internal fun ImageToolboxApplication.initNeuralTool() = NeuralTool.init(
    context = this,
    httpClient = httpClient,
    baseUrl = HF_BASE_URL
)