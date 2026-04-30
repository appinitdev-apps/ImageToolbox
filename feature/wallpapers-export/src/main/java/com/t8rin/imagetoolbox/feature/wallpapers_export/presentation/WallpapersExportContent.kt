/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.wallpapers_export.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.isInstalledFromPlayStore
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.utils.provider.rememberCurrentLifecycleEvent
import com.t8rin.imagetoolbox.core.ui.widget.AdaptiveLayoutScreen
import com.t8rin.imagetoolbox.core.ui.widget.buttons.ShareButton
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.LoadingDialog
import com.t8rin.imagetoolbox.core.ui.widget.other.FeatureNotAvailableContent
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.core.ui.widget.text.TopAppBarTitle
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee
import com.t8rin.imagetoolbox.core.ui.widget.utils.AutoContentBasedColors
import com.t8rin.imagetoolbox.core.utils.appContext
import com.t8rin.imagetoolbox.feature.wallpapers_export.domain.model.WallpapersResult
import com.t8rin.imagetoolbox.feature.wallpapers_export.presentation.components.WallpapersActionButtons
import com.t8rin.imagetoolbox.feature.wallpapers_export.presentation.components.WallpapersControls
import com.t8rin.imagetoolbox.feature.wallpapers_export.presentation.components.WallpapersPreview
import com.t8rin.imagetoolbox.feature.wallpapers_export.presentation.screenLogic.WallpapersExportComponent

@Composable
fun WallpapersExportContent(
    component: WallpapersExportComponent
) {
    if (appContext.isInstalledFromPlayStore()) {
        FeatureNotAvailableContent(
            title = {
                Text(
                    text = stringResource(R.string.wallpapers_export),
                    modifier = Modifier.marquee()
                )
            },
            onGoBack = component.onGoBack
        )
        return
    }

    val isPortrait by isPortraitOrientationAsState()
    val lifecycleEvent = rememberCurrentLifecycleEvent()

    LaunchedEffect(lifecycleEvent) {
        component.loadWallpapers()
    }

    AutoContentBasedColors(component.wallpapers.firstOrNull()?.imageUri)

    AdaptiveLayoutScreen(
        shouldDisableBackHandler = true,
        title = {
            TopAppBarTitle(
                title = stringResource(R.string.wallpapers_export),
                input = component.wallpapers.takeIf { it.isNotEmpty() },
                isLoading = component.isImageLoading,
                size = null
            )
        },
        onGoBack = component.onGoBack,
        actions = {
            ShareButton(
                enabled = component.selectedImages.isNotEmpty(),
                onShare = component::performSharing,
                onCopy = if (component.wallpapers.size == 1) {
                    { component.cacheImages { it.firstOrNull()?.let(Clipboard::copy) } }
                } else null
            )
        },
        topAppBarPersistentActions = {
            if (isPortrait) {
                TopAppBarEmoji()
            }
        },
        imagePreview = {
            WallpapersPreview(component)
        },
        controls = {
            WallpapersControls(component)
        },
        buttons = { actions ->
            WallpapersActionButtons(
                component = component,
                actions = actions
            )
        },
        placeImagePreview = component.wallpapersState is WallpapersResult.Success,
        showImagePreviewAsStickyHeader = false,
        canShowScreenData = true
    )

    LoadingDialog(
        visible = component.isSaving,
        done = component.done,
        left = component.left,
        onCancelLoading = component::cancelSaving,
        canCancel = component.isSaving
    )
}