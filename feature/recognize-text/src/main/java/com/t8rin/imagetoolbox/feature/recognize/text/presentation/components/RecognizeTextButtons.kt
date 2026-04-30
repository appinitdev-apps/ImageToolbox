/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.recognize.text.presentation.components

import androidx.compose.foundation.layout.RowScope
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.resources.icons.CopyAll
import com.t8rin.imagetoolbox.core.resources.icons.Save
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.ImagePicker
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.Picker
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFileCreator
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.buttons.BottomButtonsBlock
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeImagePickingDialog
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeSaveLocationSelectionDialog
import com.t8rin.imagetoolbox.feature.recognize.text.presentation.screenLogic.RecognizeTextComponent

@Composable
internal fun RecognizeTextButtons(
    component: RecognizeTextComponent,
    multipleImagePicker: ImagePicker,
    actions: @Composable RowScope.() -> Unit
) {
    val isPortrait by isPortraitOrientationAsState()
    val type = component.type
    val isExtraction = type is Screen.RecognizeText.Type.Extraction

    val isHaveText = component.editedText.orEmpty().isNotEmpty()

    val copyText: () -> Unit = {
        component.editedText?.let(Clipboard::copy)
    }

    var showOneTimeImagePickingDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var showFolderSelectionDialog by rememberSaveable {
        mutableStateOf(false)
    }
    val saveSearchablePdfLauncher = rememberFileCreator(
        mimeType = MimeType.Pdf,
        onSuccess = component::saveSearchablePdfTo
    )
    val save: (oneTimeSaveLocationUri: String?) -> Unit = {
        component.save(
            oneTimeSaveLocationUri = it
        )
    }
    BottomButtonsBlock(
        isNoData = type == null,
        onSecondaryButtonClick = multipleImagePicker::pickImage,
        onSecondaryButtonLongClick = {
            showOneTimeImagePickingDialog = true
        },
        onPrimaryButtonClick = {
            if (isExtraction) {
                copyText()
            } else if (type is Screen.RecognizeText.Type.WriteToSearchablePdf) {
                saveSearchablePdfLauncher.make(component.generateSearchablePdfFilename())
            } else {
                save(null)
            }
        },
        onPrimaryButtonLongClick = {
            if (isExtraction) {
                copyText()
            } else {
                showFolderSelectionDialog = true
            }
        },
        primaryButtonIcon = if (isExtraction) {
            Icons.Outlined.CopyAll
        } else {
            Icons.Rounded.Save
        },
        isPrimaryButtonVisible = if (isExtraction) isHaveText else type != null,
        actions = {
            if (isPortrait) actions()
        },
        showNullDataButtonAsContainer = true
    )
    OneTimeSaveLocationSelectionDialog(
        visible = showFolderSelectionDialog,
        onDismiss = { showFolderSelectionDialog = false },
        onSaveRequest = save
    )
    OneTimeImagePickingDialog(
        onDismiss = { showOneTimeImagePickingDialog = false },
        picker = Picker.Multiple,
        imagePicker = multipleImagePicker,
        visible = showOneTimeImagePickingDialog
    )
}