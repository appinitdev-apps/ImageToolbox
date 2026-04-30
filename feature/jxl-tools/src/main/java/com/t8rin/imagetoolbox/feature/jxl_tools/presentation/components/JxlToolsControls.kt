/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.jxl_tools.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ImageFormatSelector
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.QualitySelector
import com.t8rin.imagetoolbox.core.ui.widget.image.UrisPreview
import com.t8rin.imagetoolbox.feature.jxl_tools.presentation.screenLogic.JxlToolsComponent

@Composable
internal fun JxlToolsControls(
    component: JxlToolsComponent,
    onAddImages: () -> Unit
) {
    val isPortrait by isPortraitOrientationAsState()

    when (component.type) {
        is Screen.JxlTools.Type.JxlToImage -> {
            Spacer(modifier = Modifier.height(16.dp))
            ImageFormatSelector(
                value = component.imageFormat,
                onValueChange = component::setImageFormat,
                quality = component.params.quality,
            )
            Spacer(modifier = Modifier.height(8.dp))
            QualitySelector(
                imageFormat = component.imageFormat,
                quality = component.params.quality,
                onQualityChange = {
                    component.updateParams(
                        component.params.copy(
                            quality = it
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        is Screen.JxlTools.Type.JpegToJxl,
        is Screen.JxlTools.Type.JxlToJpeg -> {
            val uris = when (val type = component.type) {
                is Screen.JxlTools.Type.JpegToJxl -> type.jpegImageUris
                is Screen.JxlTools.Type.JxlToJpeg -> type.jxlImageUris
                is Screen.JxlTools.Type.ImageToJxl -> type.imageUris
                is Screen.JxlTools.Type.JxlToImage -> listOfNotNull(type.jxlUri)
                null -> null
            } ?: emptyList()

            UrisPreview(
                modifier = Modifier
                    .padding(
                        vertical = if (isPortrait) 24.dp else 8.dp
                    ),
                uris = uris,
                isPortrait = true,
                onRemoveUri = component::removeUri,
                onAddUris = onAddImages
            )
        }

        is Screen.JxlTools.Type.ImageToJxl -> {
            AnimatedJxlParamsSelector(
                value = component.params,
                onValueChange = component::updateParams
            )
        }

        else -> Unit
    }
}