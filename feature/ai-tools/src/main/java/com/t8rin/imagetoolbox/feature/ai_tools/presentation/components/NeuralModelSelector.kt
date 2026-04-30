/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.remote.DownloadProgress
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.MiniEdit
import com.t8rin.imagetoolbox.core.resources.icons.Neurology
import com.t8rin.imagetoolbox.core.ui.theme.ImageToolboxThemeForPreview
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem
import com.t8rin.imagetoolbox.feature.ai_tools.domain.model.NeuralModel

@Composable
internal fun NeuralModelSelector(
    value: NeuralModel?,
    onSelectModel: (NeuralModel) -> Unit,
    onDownloadModel: (NeuralModel) -> Unit,
    onDeleteModel: (NeuralModel) -> Unit,
    downloadedModels: List<NeuralModel>,
    notDownloadedModels: List<NeuralModel>,
    onImportModel: (Uri) -> Unit,
    downloadProgresses: Map<String, DownloadProgress>,
    occupiedStorageSize: Long,
    onCancelDownload: (NeuralModel) -> Unit
) {
    var showSelectionSheet by rememberSaveable {
        mutableStateOf(false)
    }

    PreferenceItem(
        modifier = Modifier.fillMaxWidth(),
        title = stringResource(id = R.string.active_model),
        subtitle = value?.title ?: stringResource(R.string.select_one_to_start),
        onClick = { showSelectionSheet = true },
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        shape = ShapeDefaults.extraLarge,
        startIcon = Icons.Outlined.Neurology,
        endIcon = Icons.Rounded.MiniEdit,
        placeBottomContentInside = true,
        bottomContent = value?.type?.let { type ->
            {
                FlowRow(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    NeuralModelTypeBadge(
                        type = type,
                        isInverted = null
                    )

                    value.speed?.let { speed ->
                        NeuralModelSpeedBadge(
                            speed = speed,
                            isInverted = null
                        )
                    }

                    NeuralModelSizeBadge(
                        model = value,
                        isInverted = false
                    )
                }
            }
        }
    )

    NeuralModelSelectionSheet(
        visible = showSelectionSheet,
        onDismiss = { showSelectionSheet = it },
        selectedModel = value,
        onSelectModel = onSelectModel,
        onDownloadModel = onDownloadModel,
        onDeleteModel = onDeleteModel,
        downloadedModels = downloadedModels,
        notDownloadedModels = notDownloadedModels,
        onImportModel = onImportModel,
        downloadProgresses = downloadProgresses,
        occupiedStorageSize = occupiedStorageSize,
        onCancelDownload = onCancelDownload
    )
}

@Preview
@Composable
private fun Preview() = ImageToolboxThemeForPreview(
    isDarkTheme = false,
    keyColor = Color.Green
) {
    NeuralModelSelector(
        value = NeuralModel.entries.first(),
        onSelectModel = {},
        onDownloadModel = {},
        onDeleteModel = {},
        downloadedModels = emptyList(),
        notDownloadedModels = emptyList(),
        onImportModel = { _ -> },
        downloadProgresses = emptyMap(),
        occupiedStorageSize = 0,
        onCancelDownload = {}
    )
}