/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.recognize.text.presentation.components

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.t8rin.imagetoolbox.core.domain.utils.humanFileSize
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.WifiTetheringError
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.other.ToastDuration
import com.t8rin.imagetoolbox.core.utils.getString
import com.t8rin.imagetoolbox.feature.recognize.text.domain.RecognitionType
import com.t8rin.imagetoolbox.feature.recognize.text.presentation.screenLogic.RecognizeTextComponent

@Composable
internal fun RecognizeTextDownloadDataDialog(component: RecognizeTextComponent) {
    val downloadDialogData = component.downloadDialogData

    if (downloadDialogData.isNotEmpty()) {
        var progress by rememberSaveable(downloadDialogData) {
            mutableFloatStateOf(0f)
        }
        var dataRemaining by rememberSaveable(downloadDialogData) {
            mutableStateOf("")
        }
        DownloadLanguageDialog(
            downloadDialogData = downloadDialogData,
            onDownloadRequest = { downloadData ->
                component.downloadTrainData(
                    type = downloadData.firstOrNull()?.type
                        ?: RecognitionType.Standard,
                    languageCode = downloadDialogData.joinToString(separator = "+") { it.languageCode },
                    onProgress = { (p, size) ->
                        dataRemaining = humanFileSize(size)
                        progress = p
                    },
                    onComplete = {
                        component.clearDownloadDialogData()
                        AppToastHost.showConfetti()
                        component.startRecognition()
                    }
                )
            },
            downloadProgress = progress,
            dataRemaining = dataRemaining,
            onNoConnection = {
                component.clearDownloadDialogData()
                AppToastHost.showToast(
                    message = getString(R.string.no_connection),
                    icon = Icons.Rounded.WifiTetheringError,
                    duration = ToastDuration.Long
                )
            },
            onDismiss = component::clearDownloadDialogData
        )
    }
}