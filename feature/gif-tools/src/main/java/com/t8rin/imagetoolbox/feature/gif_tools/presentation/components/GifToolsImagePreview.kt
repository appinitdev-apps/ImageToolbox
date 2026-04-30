/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.gif_tools.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedLoadingIndicator
import com.t8rin.imagetoolbox.core.ui.widget.image.ImagesPreviewWithSelection
import com.t8rin.imagetoolbox.core.ui.widget.image.UrisPreview
import com.t8rin.imagetoolbox.core.ui.widget.image.urisPreview
import com.t8rin.imagetoolbox.feature.gif_tools.presentation.screenLogic.GifToolsComponent

@Composable
internal fun GifToolsImagePreview(
    component: GifToolsComponent,
    onAddGifsToJxl: () -> Unit,
    onAddGifsToWebp: () -> Unit,
) {
    val isPortrait by isPortraitOrientationAsState()

    AnimatedContent(
        targetState = component.isLoading to component.type
    ) { (loading, type) ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = if (loading) {
                Modifier.padding(32.dp)
            } else Modifier
        ) {
            if (loading || type == null) {
                EnhancedLoadingIndicator()
            } else {
                when (type) {
                    is Screen.GifTools.Type.GifToImage -> {
                        ImagesPreviewWithSelection(
                            imageUris = component.convertedImageUris,
                            imageFrames = component.gifFrames,
                            onFrameSelectionChange = component::updateGifFrames,
                            isPortrait = isPortrait,
                            isLoadingImages = component.isLoadingGifImages
                        )
                    }

                    is Screen.GifTools.Type.GifToJxl -> {
                        UrisPreview(
                            modifier = Modifier.urisPreview(),
                            uris = type.gifUris ?: emptyList(),
                            isPortrait = true,
                            onRemoveUri = {
                                component.setType(
                                    Screen.GifTools.Type.GifToJxl(type.gifUris?.minus(it))
                                )
                            },
                            onAddUris = onAddGifsToJxl
                        )
                    }

                    is Screen.GifTools.Type.GifToWebp -> {
                        UrisPreview(
                            modifier = Modifier.urisPreview(),
                            uris = type.gifUris ?: emptyList(),
                            isPortrait = true,
                            onRemoveUri = {
                                component.setType(
                                    Screen.GifTools.Type.GifToWebp(type.gifUris?.minus(it))
                                )
                            },
                            onAddUris = onAddGifsToWebp
                        )
                    }

                    is Screen.GifTools.Type.ImageToGif -> Unit
                }
            }
        }
    }
}