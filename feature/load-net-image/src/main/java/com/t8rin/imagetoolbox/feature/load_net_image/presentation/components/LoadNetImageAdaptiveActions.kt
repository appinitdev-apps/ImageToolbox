/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.load_net_image.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.widget.buttons.ShareButton
import com.t8rin.imagetoolbox.core.ui.widget.buttons.ZoomButton
import com.t8rin.imagetoolbox.core.ui.widget.sheets.ZoomModalSheet
import com.t8rin.imagetoolbox.feature.load_net_image.presentation.screenLogic.LoadNetImageComponent

@Composable
internal fun RowScope.LoadNetImageAdaptiveActions(
    component: LoadNetImageComponent
) {
    AnimatedVisibility(component.parsedImages.isNotEmpty()) {
        ShareButton(
            onShare = component::performSharing,
            onCopy = {
                component.cacheCurrentImage(Clipboard::copy)
            }
        )
    }
    var showZoomSheet by rememberSaveable { mutableStateOf(false) }
    ZoomButton(
        onClick = { showZoomSheet = true },
        visible = component.bitmap != null,
    )
    ZoomModalSheet(
        data = component.bitmap,
        visible = showZoomSheet,
        onDismiss = {
            showZoomSheet = false
        }
    )
}