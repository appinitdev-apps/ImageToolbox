/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.jxl_tools.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.ImagePicker
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.Picker
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.buttons.BottomButtonsBlock
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeImagePickingDialog
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeSaveLocationSelectionDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedChip
import com.t8rin.imagetoolbox.feature.jxl_tools.presentation.screenLogic.JxlToolsComponent

@Composable
internal fun JxlToolsButtons(
    component: JxlToolsComponent,
    actions: @Composable RowScope.() -> Unit,
    onPickImage: () -> Unit,
    imagePicker: ImagePicker
) {
    val uris = when (val type = component.type) {
        is Screen.JxlTools.Type.JpegToJxl -> type.jpegImageUris
        is Screen.JxlTools.Type.JxlToJpeg -> type.jxlImageUris
        is Screen.JxlTools.Type.ImageToJxl -> type.imageUris
        is Screen.JxlTools.Type.JxlToImage -> listOfNotNull(type.jxlUri)
        null -> null
    } ?: emptyList()

    val save: (oneTimeSaveLocationUri: String?) -> Unit = {
        component.save(
            oneTimeSaveLocationUri = it
        )
    }
    var showFolderSelectionDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var showOneTimeImagePickingDialog by rememberSaveable {
        mutableStateOf(false)
    }
    BottomButtonsBlock(
        isNoData = component.type == null,
        onSecondaryButtonClick = onPickImage,
        isPrimaryButtonVisible = component.canSave,
        onPrimaryButtonClick = {
            save(null)
        },
        onPrimaryButtonLongClick = {
            showFolderSelectionDialog = true
        },
        actions = {
            if (component.type is Screen.JxlTools.Type.JxlToImage) {
                actions()
            } else {
                EnhancedChip(
                    selected = true,
                    onClick = null,
                    selectedColor = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(uris.size.toString())
                }
            }
        },
        showNullDataButtonAsContainer = true,
        onSecondaryButtonLongClick = if (component.type is Screen.JxlTools.Type.ImageToJxl || component.type == null) {
            {
                showOneTimeImagePickingDialog = true
            }
        } else null
    )
    OneTimeSaveLocationSelectionDialog(
        visible = showFolderSelectionDialog,
        onDismiss = { showFolderSelectionDialog = false },
        onSaveRequest = save
    )
    OneTimeImagePickingDialog(
        onDismiss = { showOneTimeImagePickingDialog = false },
        picker = Picker.Multiple,
        imagePicker = imagePicker,
        visible = showOneTimeImagePickingDialog
    )
}