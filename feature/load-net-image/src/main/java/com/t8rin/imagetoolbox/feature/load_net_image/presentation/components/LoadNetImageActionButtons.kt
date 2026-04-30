/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.load_net_image.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.RowScope
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ContentPaste
import com.t8rin.imagetoolbox.core.resources.icons.ImageEdit
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.widget.buttons.BottomButtonsBlock
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeSaveLocationSelectionDialog
import com.t8rin.imagetoolbox.core.ui.widget.sheets.ProcessImagesPreferenceSheet
import com.t8rin.imagetoolbox.feature.load_net_image.presentation.screenLogic.LoadNetImageComponent

@Composable
internal fun LoadNetImageActionButtons(
    component: LoadNetImageComponent,
    actions: @Composable RowScope.() -> Unit
) {
    val isPortrait by isPortraitOrientationAsState()

    val saveBitmap: (oneTimeSaveLocationUri: String?) -> Unit = {
        component.saveBitmaps(
            oneTimeSaveLocationUri = it
        )
    }

    var showFolderSelectionDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var editSheetData by remember {
        mutableStateOf(listOf<Uri>())
    }
    val noData = component.parsedImages.isEmpty()
    BottomButtonsBlock(
        isNoData = noData,
        isPrimaryButtonVisible = !noData,
        isSecondaryButtonVisible = !noData,
        secondaryButtonIcon = if (noData) Icons.Rounded.ContentPaste else Icons.Outlined.ImageEdit,
        secondaryButtonText = if (noData) stringResource(R.string.paste_link) else stringResource(R.string.edit),
        showNullDataButtonAsContainer = true,
        isScreenHaveNoDataContent = true,
        onSecondaryButtonClick = {
            if (noData) {
                Clipboard.getText(component::updateTargetUrl)
            } else {
                component.cacheImages {
                    editSheetData = it
                }
            }
        },
        onPrimaryButtonClick = {
            saveBitmap(null)
        },
        onPrimaryButtonLongClick = {
            showFolderSelectionDialog = true
        },
        actions = {
            if (isPortrait) actions()
        }
    )
    OneTimeSaveLocationSelectionDialog(
        visible = showFolderSelectionDialog,
        onDismiss = { showFolderSelectionDialog = false },
        onSaveRequest = saveBitmap,
        formatForFilenameSelection = component.getFormatForFilenameSelection()
    )

    ProcessImagesPreferenceSheet(
        uris = editSheetData,
        visible = editSheetData.isNotEmpty(),
        onDismiss = {
            editSheetData = emptyList()
        },
        onNavigate = component.onNavigate
    )
}