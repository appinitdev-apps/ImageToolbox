/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.getStringLocalized
import com.t8rin.imagetoolbox.core.utils.appContext
import com.t8rin.imagetoolbox.feature.ai_tools.domain.model.NeuralModel
import java.util.Locale

@Composable
internal fun filteredModels(
    downloadedModels: List<NeuralModel>,
    notDownloadedModels: List<NeuralModel>,
    typeFilters: List<NeuralModel.Type>,
    speedFilters: List<NeuralModel.Speed>,
    keywordFilter: String,
): State<Pair<List<NeuralModel>, List<NeuralModel>>> = remember(
    downloadedModels,
    notDownloadedModels,
    typeFilters,
    speedFilters,
    keywordFilter
) {
    derivedStateOf {
        downloadedModels.filter(
            typeFilters = typeFilters,
            speedFilters = speedFilters,
            keywordFilter = keywordFilter.trim()
        ) to notDownloadedModels.filter(
            typeFilters = typeFilters,
            speedFilters = speedFilters,
            keywordFilter = keywordFilter.trim()
        )
    }
}

private fun List<NeuralModel>.filter(
    typeFilters: List<NeuralModel.Type>,
    speedFilters: List<NeuralModel.Speed>,
    keywordFilter: String
): List<NeuralModel> {
    return if (typeFilters.isEmpty() && speedFilters.isEmpty() && keywordFilter.isBlank()) this
    else filter { model ->
        val hasType =
            typeFilters.isEmpty() || model.type == null || model.type in typeFilters
        val hasSpeed =
            speedFilters.isEmpty() || model.speed == null || speedFilters.any {
                it::class.isInstance(model.speed)
            }
        val hasKeyword =
            keywordFilter.isBlank()
                    || model.name.contains(keywordFilter, true)
                    || model.title.contains(keywordFilter, true)
                    || (model.description?.let {
                appContext.getString(model.description)
                    .contains(keywordFilter, true)
                        || appContext.getStringLocalized(model.description, Locale.ENGLISH)
                    .contains(keywordFilter, true)
            } == true)

        hasType && hasSpeed && hasKeyword
    }
}