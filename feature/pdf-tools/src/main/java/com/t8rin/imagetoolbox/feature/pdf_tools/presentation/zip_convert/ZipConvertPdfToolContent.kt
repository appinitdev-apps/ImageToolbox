/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.zip_convert

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceAtLeast
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.utils.helper.ImageUtils.rememberPdfPages
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolContent
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.PdfPreviewItem
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.zip_convert.screenLogic.ZipConvertPdfToolComponent
import kotlin.math.roundToInt

@Composable
fun ZipConvertPdfToolContent(
    component: ZipConvertPdfToolComponent
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
        title = stringResource(R.string.zip_pdf),
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

            EnhancedSliderItem(
                value = component.interval,
                title = stringResource(R.string.interval),
                internalStateTransformation = {
                    it.roundToInt()
                },
                onValueChange = {
                    component.updateInterval(it.roundToInt())
                },
                valueRange = 1f..pagesCount.fastCoerceAtLeast(1).toFloat(),
                shape = ShapeDefaults.large
            )
        },
        onFilledPassword = {
            component.setUri(component.uri)
        }
    )
}