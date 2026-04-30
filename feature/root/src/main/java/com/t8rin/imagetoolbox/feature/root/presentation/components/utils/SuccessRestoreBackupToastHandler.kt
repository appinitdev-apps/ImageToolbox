/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.root.presentation.components.utils

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Save
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalComponentActivity
import com.t8rin.imagetoolbox.feature.root.presentation.screenLogic.RootComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
internal fun SuccessRestoreBackupToastHandler(component: RootComponent) {
    val context = LocalComponentActivity.current
    LaunchedEffect(component) {
        component.backupRestoredEvents.collectLatest { restored ->
            if (restored) {
                launch {

                    //Wait for confetti to appear, then trigger font scale adjustment
                    delay(300L)
                    context.recreate()
                }
                AppToastHost.showToast(
                    message = context.getString(R.string.settings_restored),
                    icon = Icons.Rounded.Save
                )
            }
        }
    }
}