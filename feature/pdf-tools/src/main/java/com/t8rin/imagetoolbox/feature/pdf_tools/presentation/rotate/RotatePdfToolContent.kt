/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.rotate

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.data.coil.PdfImageRequest
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.utils.helper.ImageUtils.rememberPdfPages
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolContent
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.PdfPreviewItem
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.rotate.components.PdfPagesRotationGrid
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.rotate.screenLogic.RotatePdfToolComponent

@Composable
fun RotatePdfToolContent(
    component: RotatePdfToolComponent
) {
    val pagesCount by rememberPdfPages(component.uri)

    LaunchedEffect(pagesCount, component.rotations) {
        if (pagesCount > 0 && (component.rotations.isEmpty() || component.rotations.size != pagesCount)) {
            component.updateRotations(List(pagesCount) { 0 })
        }
    }

    BasePdfToolContent(
        component = component,
        contentPicker = rememberFilePicker(
            mimeType = MimeType.Pdf,
            onSuccess = component::setUri
        ),
        isPickedAlready = component.initialUri != null,
        canShowScreenData = component.uri != null,
        title = stringResource(R.string.rotate_pdf),
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
            PdfPagesRotationGrid(
                pages = remember(pagesCount, component.uri) {
                    List(pagesCount) {
                        PdfImageRequest(
                            data = component.uri,
                            pdfPage = it
                        )
                    }
                },
                rotations = component.rotations,
                onRotateAll = {
                    component.updateRotations(
                        component.rotations.map { (it + 90) % 360 }
                    )
                },
                onClearAll = {
                    component.updateRotations(List(pagesCount) { 0 })
                },
                onAutoClick = component::autoRotate,
                onRotateAt = {
                    component.updateRotations(
                        component.rotations.mapIndexed { index, rotation ->
                            if (index == it) (rotation + 90) % 360
                            else rotation
                        }
                    )
                }
            )
        },
        onFilledPassword = {
            component.setUri(component.uri)
        }
    )
}