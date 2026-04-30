/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.root.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.t8rin.imagetoolbox.core.ui.utils.provider.ImageToolboxCompositionLocals
import com.t8rin.imagetoolbox.feature.root.presentation.components.RootDialogs
import com.t8rin.imagetoolbox.feature.root.presentation.components.ScreenSelector
import com.t8rin.imagetoolbox.feature.root.presentation.components.utils.uiSettingsState
import com.t8rin.imagetoolbox.feature.root.presentation.screenLogic.RootComponent

@Composable
fun RootContent(
    component: RootComponent
) {
    val stack by component.childStack.subscribeAsState()

    ImageToolboxCompositionLocals(
        settingsState = component.uiSettingsState(),
        filterPreviewModel = component.filterPreviewModel,
        canSetDynamicFilterPreview = component.canSetDynamicFilterPreview,
        currentScreen = stack.items.lastOrNull()?.configuration
    ) {
        ScreenSelector(component)

        RootDialogs(component)
    }
}