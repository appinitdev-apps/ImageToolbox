/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.sheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.t8rin.imagetoolbox.core.resources.Icons
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.FileDownloadOff
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.isInstalledFromPlayStore
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalComponentActivity
import com.t8rin.imagetoolbox.core.utils.getString

@Composable
internal fun UpdateSheetImpl(
    changelog: String,
    tag: String,
    visible: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalComponentActivity.current

    if (context.isInstalledFromPlayStore()) {
        LaunchedEffect(visible) {
            if (visible) {
                runCatching {
                    val appUpdateManager = AppUpdateManagerFactory.create(context)

                    val appUpdateInfoTask = appUpdateManager.appUpdateInfo

                    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
                        if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                        ) {
                            appUpdateManager.startUpdateFlow(
                                appUpdateInfo,
                                context,
                                AppUpdateOptions.defaultOptions(AppUpdateType.IMMEDIATE)
                            )
                        } else {
                            AppToastHost.showToast(
                                icon = Icons.Rounded.FileDownloadOff,
                                message = getString(R.string.no_updates)
                            )
                        }
                    }
                }.onFailure {
                    AppToastHost.showToast(
                        icon = Icons.Rounded.FileDownloadOff,
                        message = getString(R.string.no_updates)
                    )
                }
            }
        }
    } else {
        DefaultUpdateSheet(
            changelog = changelog,
            tag = tag,
            visible = visible,
            onDismiss = onDismiss
        )
    }
}