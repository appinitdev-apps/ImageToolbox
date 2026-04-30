/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.images_to_pdf

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.domain.image.model.Preset
import com.t8rin.imagetoolbox.core.domain.image.model.Quality
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.AddPhotoAlt
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberImagePicker
import com.t8rin.imagetoolbox.core.ui.widget.controls.ImageReorderCarousel
import com.t8rin.imagetoolbox.core.ui.widget.controls.ScaleSmallImagesToLargeToggle
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.PresetSelector
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.QualitySelector
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolContent
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.images_to_pdf.screenLogic.ImagesToPdfToolComponent

@Composable
fun ImagesToPdfToolContent(
    component: ImagesToPdfToolComponent
) {
    val addImagesToPdfPicker = rememberImagePicker(onSuccess = component::addUris)

    BasePdfToolContent(
        component = component,
        contentPicker = rememberImagePicker(
            onSuccess = component::setUris
        ),
        secondaryButtonIcon = Icons.Rounded.AddPhotoAlt,
        secondaryButtonText = stringResource(R.string.pick_image_alt),
        noDataText = stringResource(R.string.pick_image),
        isPickedAlready = component.initialUris != null,
        canShowScreenData = !component.uris.isNullOrEmpty(),
        title = stringResource(R.string.images_to_pdf),
        controls = {
            ImageReorderCarousel(
                images = component.uris,
                onReorder = component::setUris,
                onNeedToAddImage = addImagesToPdfPicker::pickImage,
                onNeedToRemoveImageAt = component::removeAt,
                onNavigate = component.onNavigate
            )
            Spacer(Modifier.height(16.dp))
            PresetSelector(
                value = component.presetSelected,
                includeTelegramOption = false,
                onValueChange = {
                    if (it is Preset.Percentage) {
                        component.selectPreset(it)
                    }
                }
            )
            Spacer(Modifier.height(8.dp))
            QualitySelector(
                imageFormat = ImageFormat.Jpg,
                quality = Quality.Base(component.quality),
                onQualityChange = {
                    component.setQuality(it.qualityValue)
                },
                autoCoerce = false
            )
            Spacer(Modifier.height(8.dp))
            ScaleSmallImagesToLargeToggle(
                checked = component.scaleSmallImagesToLarge,
                onCheckedChange = {
                    component.toggleScaleSmallImagesToLarge()
                }
            )
        }
    )
}