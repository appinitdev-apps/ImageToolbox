/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.LocationSearching
import com.t8rin.imagetoolbox.core.resources.icons.MiniEdit
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeSaveLocationSelectionDialog
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun OneTimeSaveLocationSettingItem(
    shape: Shape = ShapeDefaults.default,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    PreferenceItem(
        shape = shape,
        onClick = { showDialog = true },
        title = stringResource(R.string.one_time_save_location),
        subtitle = stringResource(R.string.one_time_save_location_sub),
        startIcon = Icons.Rounded.LocationSearching,
        endIcon = Icons.Rounded.MiniEdit,
        modifier = modifier
    )
    OneTimeSaveLocationSelectionDialog(
        visible = showDialog,
        onDismiss = { showDialog = false },
        onSaveRequest = null
    )
}