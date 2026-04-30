/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.color_library.presentation.screenLogic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import com.arkivanov.decompose.ComponentContext
import com.t8rin.colors.parser.ColorNameParser
import com.t8rin.colors.parser.ColorWithName
import com.t8rin.imagetoolbox.color_library.presentation.components.FavoriteColors
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.saving.FileController
import com.t8rin.imagetoolbox.core.domain.utils.ListUtils.toggle
import com.t8rin.imagetoolbox.core.domain.utils.update
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.helper.toHex
import com.t8rin.imagetoolbox.core.ui.utils.state.savable
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest

class ColorLibraryComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted val onGoBack: () -> Unit,
    fileController: FileController,
    dispatchersHolder: DispatchersHolder
) : BaseComponent(dispatchersHolder, componentContext) {

    private var baseColors: List<ColorWithName> = emptyList()

    private val _colors: MutableState<List<ColorWithName>> = mutableStateOf(emptyList())
    val colors by _colors

    private val _searchKeyword: MutableState<String> = mutableStateOf("")
    val searchKeyword by _searchKeyword

    private val _favoriteColors = fileController.savable(
        scope = componentScope,
        initial = FavoriteColors(),
    )
    val favoriteColors get() = _favoriteColors.get().colors

    init {
        componentScope.launch {
            setColors(ColorNameParser.colorNames.values.sortedBy { it.name }.toList())

            snapshotFlow { favoriteColors }
                .collectLatest { favorite ->
                    setColors(
                        baseColors.sortedBy { it.name !in favorite }
                    )
                    if (searchKeyword.isNotBlank()) {
                        updateSearch()
                    }
                }
        }
    }

    private fun setColors(newColors: List<ColorWithName>) {
        baseColors = newColors
        _colors.value = newColors
    }

    fun updateSearch(keyword: String) {
        _searchKeyword.value = keyword

        if (keyword.isBlank()) {
            updateSearch()
            return
        }

        debouncedImageCalculation(
            delay = 350,
            action = { updateSearch() }
        )
    }

    private fun updateSearch() {
        if (searchKeyword.isBlank()) {
            _colors.value = baseColors
        } else {
            _colors.value = baseColors.filter {
                it.name.contains(
                    other = searchKeyword,
                    ignoreCase = true
                ) || searchKeyword.contains(
                    other = it.name,
                    ignoreCase = true
                ) || it.color.toHex().contains(
                    other = searchKeyword,
                    ignoreCase = true
                )
            }
        }
    }

    fun clearSearch() {
        updateSearch("")
    }

    fun toggleFavoriteColor(color: ColorWithName) {
        _favoriteColors.update { it.copy(colors = it.colors.toggle(color.name)) }
    }

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onGoBack: () -> Unit,
        ): ColorLibraryComponent
    }

}