/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.color_tools.presentation.screenLogic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.saving.FileController
import com.t8rin.imagetoolbox.core.domain.utils.update
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.state.savable
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ColorToolsComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted val onGoBack: () -> Unit,
    fileController: FileController,
    dispatchersHolder: DispatchersHolder
) : BaseComponent(dispatchersHolder, componentContext) {

    private val _selectedColor: MutableState<Color> = mutableStateOf(Color.Unspecified)
    val selectedColor: Color by _selectedColor

    private val _isPinned = fileController.savable(
        delay = 750,
        scope = componentScope,
        initial = false,
        key = "ColorToolsComponent"
    )
    val isPinned: Boolean by _isPinned

    fun updateIsPinned(isPinned: Boolean) {
        _isPinned.update { isPinned }
    }

    fun updateSelectedColor(newColor: Color) {
        _selectedColor.update { newColor }
    }

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onGoBack: () -> Unit,
        ): ColorToolsComponent
    }

}