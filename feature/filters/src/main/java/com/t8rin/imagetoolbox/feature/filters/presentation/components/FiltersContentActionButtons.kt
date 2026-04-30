/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.presentation.components

import androidx.compose.foundation.layout.RowScope
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.t8rin.imagetoolbox.core.resources.icons.AutoFixHigh
import com.t8rin.imagetoolbox.core.resources.icons.Texture
import com.t8rin.imagetoolbox.core.ui.theme.mixedContainer
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.ImagePicker
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.Picker
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.buttons.BottomButtonsBlock
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeImagePickingDialog
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeSaveLocationSelectionDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedFloatingActionButton
import com.t8rin.imagetoolbox.feature.filters.presentation.screenLogic.FiltersComponent

@Composable
internal fun FiltersContentActionButtons(
    component: FiltersComponent,
    actions: @Composable RowScope.() -> Unit,
    imagePicker: ImagePicker,
    pickSingleImagePicker: ImagePicker,
    selectionFilterPicker: ImagePicker,
) {
    val isPortrait by isPortraitOrientationAsState()

    val filterType = component.filterType

    val saveBitmaps: (oneTimeSaveLocationUri: String?) -> Unit = {
        when (filterType) {
            is Screen.Filter.Type.Basic -> {
                component.saveBitmaps(
                    oneTimeSaveLocationUri = it
                )
            }

            is Screen.Filter.Type.Masking -> {
                component.saveMaskedBitmap(
                    oneTimeSaveLocationUri = it
                )
            }

            else -> Unit
        }
    }
    var showFolderSelectionDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var showOneTimeImagePickingDialog by rememberSaveable {
        mutableStateOf(false)
    }
    BottomButtonsBlock(
        isNoData = component.basicFilterState.uris.isNullOrEmpty() && component.maskingFilterState.uri == null,
        onSecondaryButtonClick = {
            when (filterType) {
                is Screen.Filter.Type.Basic -> imagePicker.pickImage()
                is Screen.Filter.Type.Masking -> pickSingleImagePicker.pickImage()
                null -> selectionFilterPicker.pickImage()
            }
        },
        onPrimaryButtonClick = {
            saveBitmaps(null)
        },
        onPrimaryButtonLongClick = {
            showFolderSelectionDialog = true
        },
        isPrimaryButtonVisible = component.canSave,
        middleFab = {
            EnhancedFloatingActionButton(
                onClick = component::showAddFiltersSheet,
                containerColor = MaterialTheme.colorScheme.mixedContainer
            ) {
                when (filterType) {
                    is Screen.Filter.Type.Basic -> {
                        Icon(
                            imageVector = Icons.Rounded.AutoFixHigh,
                            contentDescription = null
                        )
                    }

                    is Screen.Filter.Type.Masking -> {
                        Icon(
                            imageVector = Icons.Outlined.Texture,
                            contentDescription = null
                        )
                    }

                    null -> Unit
                }
            }

        },
        actions = {
            if (isPortrait) actions()
        },
        onSecondaryButtonLongClick = {
            showOneTimeImagePickingDialog = true
        },
        showNullDataButtonAsContainer = true
    )
    OneTimeSaveLocationSelectionDialog(
        visible = showFolderSelectionDialog,
        onDismiss = { showFolderSelectionDialog = false },
        onSaveRequest = saveBitmaps,
        formatForFilenameSelection = component.getFormatForFilenameSelection()
    )
    OneTimeImagePickingDialog(
        onDismiss = { showOneTimeImagePickingDialog = false },
        picker = if (filterType !is Screen.Filter.Type.Masking) {
            Picker.Multiple
        } else {
            Picker.Single
        },
        imagePicker = selectionFilterPicker,
        visible = showOneTimeImagePickingDialog
    )
}