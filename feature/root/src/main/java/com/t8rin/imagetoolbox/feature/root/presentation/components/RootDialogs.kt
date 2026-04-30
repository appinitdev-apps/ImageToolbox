/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.root.presentation.components

import androidx.compose.runtime.Composable
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalEditPresetsController
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.utils.helper.ReviewHandler
import com.t8rin.imagetoolbox.core.ui.widget.sheets.ProcessImagesPreferenceSheet
import com.t8rin.imagetoolbox.core.ui.widget.sheets.UpdateSheet
import com.t8rin.imagetoolbox.feature.root.presentation.components.dialogs.AppExitDialog
import com.t8rin.imagetoolbox.feature.root.presentation.components.dialogs.EditPresetsSheet

import com.t8rin.imagetoolbox.feature.root.presentation.components.dialogs.PermissionDialog
import com.t8rin.imagetoolbox.feature.root.presentation.components.utils.SuccessRestoreBackupToastHandler
import com.t8rin.imagetoolbox.feature.root.presentation.screenLogic.RootComponent

@Composable
internal fun RootDialogs(component: RootComponent) {
    val editPresetsController = LocalEditPresetsController.current

    AppExitDialog(component)

    EditPresetsSheet(
        visible = editPresetsController.isVisible,
        onDismiss = editPresetsController::close,
        onUpdatePresets = component::setPresets
    )

    ProcessImagesPreferenceSheet(
        uris = component.uris ?: emptyList(),
        extraDataType = component.extraDataType,
        visible = component.showSelectDialog,
        onDismiss = component::hideSelectDialog,
        onNavigate = { screen ->
            component.navigateTo(screen)
            Clipboard.clear()
        }
    )

//    UpdateSheet(
//        tag = component.tag,
//        changelog = component.changelog,
//        visible = component.showUpdateDialog,
//        onDismiss = component::cancelledUpdate
//    )
//
//    FirstLaunchSetupDialog(
//        toggleShowUpdateDialog = component::toggleShowUpdateDialog,
//        toggleAllowBetas = component::toggleAllowBetas,
//        adjustPerformance = component::adjustPerformance
//    )
//
//    DonateDialog(
//        onRegisterDonateDialogOpen = component::registerDonateDialogOpen,
//        onNotShowDonateDialogAgain = component::notShowDonateDialogAgain
//    )

    PermissionDialog()

//    GithubReviewDialog(
//        visible = component.showGithubReviewDialog,
//        onDismiss = component::hideReviewDialog,
//        onNotShowAgain = ReviewHandler.current::notShowReviewAgain,
//        isNotShowAgainButtonVisible = ReviewHandler.current.showNotShowAgainButton
//    )
//
//    TelegramGroupDialog(
//        visible = component.showTelegramGroupDialog,
//        onDismiss = component::hideTelegramGroupDialog,
//        onRedirected = component::registerTelegramGroupOpen
//    )

    SuccessRestoreBackupToastHandler(component)

//    HandleLookForUpdates(component)
}