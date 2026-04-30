/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.gif_tools.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberImagePicker
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.controls.ImageReorderCarousel
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ImageFormatSelector
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.QualitySelector
import com.t8rin.imagetoolbox.feature.gif_tools.presentation.screenLogic.GifToolsComponent

@Composable
internal fun GifToolsControls(component: GifToolsComponent) {
    when (val type = component.type) {
        is Screen.GifTools.Type.GifToImage -> {
            Spacer(modifier = Modifier.height(16.dp))
            ImageFormatSelector(
                value = component.imageFormat,
                onValueChange = component::setImageFormat,
                quality = component.params.quality
            )
            Spacer(modifier = Modifier.height(8.dp))
            QualitySelector(
                imageFormat = component.imageFormat,
                quality = component.params.quality,
                onQualityChange = component::setQuality
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        is Screen.GifTools.Type.ImageToGif -> {
            val addImagesToGifPicker =
                rememberImagePicker(onSuccess = component::addImageToUris)
            Spacer(modifier = Modifier.height(16.dp))
            ImageReorderCarousel(
                images = type.imageUris,
                onReorder = component::reorderImageUris,
                onNeedToAddImage = addImagesToGifPicker::pickImage,
                onNeedToRemoveImageAt = component::removeImageAt,
                onNavigate = component.onNavigate
            )
            Spacer(modifier = Modifier.height(8.dp))
            GifParamsSelector(
                value = component.params,
                onValueChange = component::updateParams
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        is Screen.GifTools.Type.GifToJxl -> {
            QualitySelector(
                imageFormat = ImageFormat.Jxl.Lossy,
                quality = component.jxlQuality,
                onQualityChange = component::setJxlQuality
            )
        }

        is Screen.GifTools.Type.GifToWebp -> {
            QualitySelector(
                imageFormat = ImageFormat.Jpg,
                quality = component.webpQuality,
                onQualityChange = component::setWebpQuality
            )
        }

        null -> Unit
    }
}