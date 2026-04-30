/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.load_net_image.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.widget.image.ImagesPreviewWithSelection
import com.t8rin.imagetoolbox.core.ui.widget.modifier.negativePadding
import com.t8rin.imagetoolbox.feature.load_net_image.presentation.screenLogic.LoadNetImageComponent

@Composable
internal fun ParsedImagesSelection(
    component: LoadNetImageComponent
) {
    AnimatedVisibility(component.parsedImages.size > 1) {
        ImagesPreviewWithSelection(
            imageUris = component.parsedImages,
            imageFrames = component.imageFrames,
            onFrameSelectionChange = component::updateImageFrames,
            isPortrait = true,
            isLoadingImages = component.isImageLoading,
            contentScale = ContentScale.Fit,
            contentPadding = PaddingValues(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    (130.dp * component.parsedImages.size).coerceAtMost(420.dp)
                )
                .negativePadding(horizontal = 20.dp),
            showExtension = false
        )
    }
}