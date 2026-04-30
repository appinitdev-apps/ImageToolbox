/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.remove_pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.data.coil.PdfImageRequest
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.domain.utils.ListUtils.toggle
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.utils.helper.ImageUtils.rememberPdfPages
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolContent
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.PdfPreviewItem
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.remove_pages.components.PdfPagesRemoveGrid
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.remove_pages.screenLogic.RemovePagesPdfToolComponent

@Composable
fun RemovePagesPdfToolContent(
    component: RemovePagesPdfToolComponent
) {
    val pagesCount by rememberPdfPages(component.uri)

    BasePdfToolContent(
        component = component,
        contentPicker = rememberFilePicker(
            mimeType = MimeType.Pdf,
            onSuccess = component::setUri
        ),
        isPickedAlready = component.initialUri != null,
        canShowScreenData = component.uri != null,
        title = stringResource(R.string.remove_pages_pdf),
        canSave = component.pagesToDelete.size < pagesCount,
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
            PdfPagesRemoveGrid(
                pages = remember(pagesCount, component.uri) {
                    List(pagesCount) {
                        PdfImageRequest(
                            data = component.uri,
                            pdfPage = it
                        )
                    }
                },
                pagesToDelete = component.pagesToDelete,
                onClearAll = {
                    component.updatePages(emptyList())
                },
                onClickPage = {
                    component.updatePages(
                        component.pagesToDelete.toggle(it)
                    )
                },
                onUpdatePages = component::updatePages,
                pagesCount = pagesCount
            )
        },
        onFilledPassword = {
            component.setUri(component.uri)
        }
    )
}