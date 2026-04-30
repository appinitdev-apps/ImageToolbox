/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.page_numbers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.utils.helper.ImageUtils.rememberPdfPages
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ColorRowSelector
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.PositionSelector
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolContent
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.page_numbers.components.PageNumbersPreview
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.page_numbers.screenLogic.PageNumbersPdfToolComponent

@Composable
fun PageNumbersPdfToolContent(
    component: PageNumbersPdfToolComponent
) {
    val pageCount by rememberPdfPages(component.uri)
    val params = component.params

    BasePdfToolContent(
        component = component,
        contentPicker = rememberFilePicker(
            mimeType = MimeType.Pdf,
            onSuccess = component::setUri
        ),
        isPickedAlready = component.initialUri != null,
        canShowScreenData = component.uri != null,
        title = stringResource(R.string.page_numbers),
        actions = {},
        imagePreview = {
            PageNumbersPreview(
                uri = component.uri,
                params = params,
                pageCount = pageCount
            )
        },
        placeImagePreview = true,
        showImagePreviewAsStickyHeader = true,
        controls = {
            RoundedTextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .container(
                        shape = MaterialTheme.shapes.large,
                        resultPadding = 8.dp
                    ),
                value = params.labelFormat,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = false,
                onValueChange = {
                    component.updateParams(
                        params.copy(
                            labelFormat = it
                        )
                    )
                },
                label = {
                    Text(stringResource(R.string.label_format))
                }
            )
            Spacer(Modifier.height(8.dp))
            PositionSelector(
                value = params.position,
                onValueChange = {
                    component.updateParams(
                        params.copy(
                            position = it
                        )
                    )
                },
                color = Color.Unspecified
            )
            Spacer(Modifier.height(8.dp))
            ColorRowSelector(
                value = Color(params.color),
                onValueChange = {
                    component.updateParams(
                        params.copy(
                            color = it.toArgb()
                        )
                    )
                },
                title = stringResource(R.string.text_color),
                modifier = Modifier.container(
                    shape = ShapeDefaults.large
                )
            )
        },
        onFilledPassword = {
            component.setUri(component.uri)
        }
    )
}