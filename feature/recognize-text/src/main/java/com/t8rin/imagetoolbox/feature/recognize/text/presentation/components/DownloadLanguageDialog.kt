/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.recognize.text.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Download
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.isNetworkAvailable
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.BasicEnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedLoadingIndicator
import com.t8rin.imagetoolbox.core.ui.widget.text.AutoSizeText

@Composable
fun DownloadLanguageDialog(
    downloadDialogData: List<UiDownloadData>,
    onDownloadRequest: (List<UiDownloadData>) -> Unit,
    downloadProgress: Float,
    dataRemaining: String,
    onNoConnection: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var downloadStarted by rememberSaveable(downloadDialogData) {
        mutableStateOf(false)
    }

    EnhancedAlertDialog(
        visible = !downloadStarted,
        icon = {
            Icon(
                imageVector = Icons.Rounded.Download,
                contentDescription = null
            )
        },
        title = { Text(stringResource(id = R.string.no_data)) },
        text = {
            Text(
                stringResource(
                    id = R.string.download_description,
                    downloadDialogData.firstOrNull()?.type?.displayName ?: "",
                    downloadDialogData.joinToString(separator = ", ") { it.localizedName }
                )
            )
        },
        onDismissRequest = {},
        confirmButton = {
            EnhancedButton(
                onClick = {
                    if (context.isNetworkAvailable()) {
                        downloadDialogData.let { downloadData ->
                            onDownloadRequest(downloadData)
                            downloadStarted = true
                        }
                    } else onNoConnection()
                }
            ) {
                Text(stringResource(R.string.download))
            }
        },
        dismissButton = {
            EnhancedButton(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.close))
            }
        }
    )

    BasicEnhancedAlertDialog(
        onDismissRequest = {},
        visible = downloadStarted,
        modifier = Modifier.fillMaxSize()
    ) {
        EnhancedLoadingIndicator(
            progress = downloadProgress,
            loaderSize = 64.dp
        ) {
            AutoSizeText(
                text = dataRemaining,
                maxLines = 1,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.width(it * 0.8f),
                textAlign = TextAlign.Center,
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp,
                    lineHeight = 12.sp
                )
            )
        }
    }
}