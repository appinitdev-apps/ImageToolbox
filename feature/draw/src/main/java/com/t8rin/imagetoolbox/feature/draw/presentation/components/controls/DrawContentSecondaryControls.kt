/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.draw.presentation.components.controls

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.t8rin.imagetoolbox.core.resources.icons.Redo
import com.t8rin.imagetoolbox.core.resources.icons.Undo
import com.t8rin.imagetoolbox.core.ui.widget.buttons.EraseModeButton
import com.t8rin.imagetoolbox.core.ui.widget.buttons.PanModeButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.feature.draw.presentation.screenLogic.DrawComponent

@Composable
internal fun DrawContentSecondaryControls(
    component: DrawComponent,
    panEnabled: Boolean,
    onTogglePanEnabled: () -> Unit,
    isEraserOn: Boolean,
    onToggleIsEraserOn: () -> Unit
) {
    PanModeButton(
        selected = panEnabled,
        onClick = onTogglePanEnabled
    )
    EnhancedIconButton(
        onClick = component::undo,
        enabled = component.lastPaths.isNotEmpty() || component.paths.isNotEmpty()
    ) {
        Icon(
            imageVector = Icons.Rounded.Undo,
            contentDescription = "Undo"
        )
    }
    EnhancedIconButton(
        onClick = component::redo,
        enabled = component.undonePaths.isNotEmpty()
    ) {
        Icon(
            imageVector = Icons.Rounded.Redo,
            contentDescription = "Redo"
        )
    }
    EraseModeButton(
        selected = isEraserOn,
        enabled = !panEnabled,
        onClick = onToggleIsEraserOn
    )
}