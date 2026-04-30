/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost

@Composable
fun AutoFilePicker(
    onAutoPick: () -> Unit,
    isPickedAlready: Boolean
) {
    val settingsState = LocalSettingsState.current

    var picked by rememberSaveable(isPickedAlready) {
        mutableStateOf(isPickedAlready)
    }
    LaunchedEffect(Unit) {
        if (settingsState.skipImagePicking && !picked) {
            runCatching {
                onAutoPick()
                picked = true
            }.onFailure(AppToastHost::handleFileSystemFailure)
        }
    }
}