/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.cipher.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.model.CipherType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.FileOpen
import com.t8rin.imagetoolbox.core.resources.icons.KeyVertical
import com.t8rin.imagetoolbox.core.resources.icons.Lock
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.widget.AdaptiveLayoutScreen
import com.t8rin.imagetoolbox.core.ui.widget.buttons.BottomButtonsBlock
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.ExitWithoutSavingDialog
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.LoadingDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedBadge
import com.t8rin.imagetoolbox.core.ui.widget.image.AutoFilePicker
import com.t8rin.imagetoolbox.core.ui.widget.image.FileNotPickedWidget
import com.t8rin.imagetoolbox.core.ui.widget.modifier.scaleOnTap
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee
import com.t8rin.imagetoolbox.feature.cipher.domain.WrongKeyException
import com.t8rin.imagetoolbox.feature.cipher.presentation.components.CipherControls
import com.t8rin.imagetoolbox.feature.cipher.presentation.components.CipherTipSheet
import com.t8rin.imagetoolbox.feature.cipher.presentation.screenLogic.CipherComponent


@Composable
fun CipherContent(
    component: CipherComponent
) {
    val showTip = component.showTip

    var showExitDialog by rememberSaveable { mutableStateOf(false) }

    val canGoBack = component.canGoBack
    val key = component.key

    val onBack = {
        if (!canGoBack) showExitDialog = true
        else component.onGoBack()
    }

    val filePicker = rememberFilePicker(onSuccess = component::setUri)

    AutoFilePicker(
        onAutoPick = filePicker::pickFile,
        isPickedAlready = component.initialUri != null
    )

    val isPortrait by isPortraitOrientationAsState()

    AdaptiveLayoutScreen(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.marquee()
            ) {
                AnimatedContent(
                    targetState = component.uri to component.isEncrypt,
                    transitionSpec = { fadeIn() togetherWith fadeOut() }
                ) { (uri, isEncrypt) ->
                    Text(
                        if (uri == null) {
                            stringResource(R.string.cipher)
                        } else {
                            listOf(
                                stringResource(R.string.encryption),
                                stringResource(R.string.decryption)
                            )[if (isEncrypt) 0 else 1]
                        },
                        textAlign = TextAlign.Center
                    )
                }
                EnhancedBadge(
                    content = {
                        Text(
                            text = CipherType.entries.size.toString()
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .padding(bottom = 12.dp)
                        .scaleOnTap {
                            AppToastHost.showConfetti()
                        }
                )
            }
        },
        onGoBack = onBack,
        shouldDisableBackHandler = canGoBack,
        topAppBarPersistentActions = {
            TopAppBarEmoji()
        },
        actions = {},
        buttons = {
            BottomButtonsBlock(
                isNoData = component.uri == null,
                onSecondaryButtonClick = filePicker::pickFile,
                secondaryButtonIcon = Icons.Rounded.FileOpen,
                secondaryButtonText = stringResource(R.string.pick_file),
                onPrimaryButtonClick = {
                    component.startCryptography {
                        if (it is WrongKeyException) {
                            AppToastHost.showFailureToast(R.string.invalid_password_or_not_encrypted)
                        } else if (it != null) {
                            AppToastHost.showFailureToast(
                                throwable = it
                            )
                        }
                    }
                },
                primaryButtonIcon = if (component.isEncrypt) {
                    Icons.Rounded.Lock
                } else {
                    Icons.Rounded.KeyVertical
                },
                primaryButtonText = if (isPortrait) {
                    if (component.isEncrypt) {
                        stringResource(R.string.encrypt)
                    } else {
                        stringResource(R.string.decrypt)
                    }
                } else "",
                isPrimaryButtonEnabled = key.isNotEmpty(),
                actions = {}
            )
        },
        canShowScreenData = component.uri != null,
        noDataControls = {
            FileNotPickedWidget(onPickFile = filePicker::pickFile)
        },
        controls = {
            CipherControls(component)
        },
        imagePreview = {},
        showImagePreviewAsStickyHeader = false,
        placeImagePreview = false,
        showActionsInTopAppBar = false,
        addHorizontalCutoutPaddingIfNoPreview = false,
    )

    ExitWithoutSavingDialog(
        onExit = component.onGoBack,
        onDismiss = { showExitDialog = false },
        visible = showExitDialog
    )

    CipherTipSheet(
        visible = showTip,
        onDismiss = component::hideTip
    )

    LoadingDialog(
        visible = component.isSaving,
        onCancelLoading = component::cancelSaving
    )

}