/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.metadata

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolContent
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.PdfPreviewItem
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.metadata.components.MetadataEditor
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.metadata.screenLogic.MetadataPdfToolComponent

@Composable
fun MetadataPdfToolContent(
    component: MetadataPdfToolComponent
) {
    BasePdfToolContent(
        component = component,
        contentPicker = rememberFilePicker(
            mimeType = MimeType.Pdf,
            onSuccess = component::setUri
        ),
        isPickedAlready = component.initialUri != null,
        canShowScreenData = component.uri != null,
        title = stringResource(R.string.metadata),
        controls = {
            component.uri?.let {
                PdfPreviewItem(
                    uri = it,
                    onRemove = {
                        component.setUri(null)
                    }
                )
                Spacer(Modifier.height(16.dp))
            }

            MetadataEditor(
                value = component.metadata,
                onValueChange = component::updateMetadata,
                deepClean = component.deepClean,
                onReset = component::resetMetadata,
                onUpdateDeepClean = component::updateDeepClean
            )
        },
        onFilledPassword = {
            component.setUri(component.uri)
        }
    )
}