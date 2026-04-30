/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.UploadFile
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFileCreator
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun BackupSettingItem(
    onCreateBackupFilename: () -> String,
    onCreateBackup: (Uri) -> Unit,
    shape: Shape = ShapeDefaults.top,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    val backupSavingLauncher = rememberFileCreator(onSuccess = onCreateBackup)

    PreferenceItem(
        onClick = {
            backupSavingLauncher.make(onCreateBackupFilename())
        },
        shape = shape,
        modifier = modifier,
        title = stringResource(R.string.backup),
        subtitle = stringResource(R.string.backup_sub),
        startIcon = Icons.Outlined.UploadFile
    )
}