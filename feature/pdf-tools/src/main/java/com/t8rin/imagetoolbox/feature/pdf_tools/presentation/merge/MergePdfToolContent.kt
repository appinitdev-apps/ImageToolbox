/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.merge

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.widget.controls.FileReorderVerticalList
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolContent
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.merge.screenLogic.MergePdfToolComponent

@Composable
fun MergePdfToolContent(
    component: MergePdfToolComponent
) {
    BasePdfToolContent(
        component = component,
        contentPicker = rememberFilePicker(
            mimeType = MimeType.Pdf,
            onSuccess = component::setUris
        ),
        isPickedAlready = !component.initialUris.isNullOrEmpty(),
        canShowScreenData = component.uris.isNotEmpty(),
        title = stringResource(R.string.merge_pdf),
        controls = {
            val addFilesPicker = rememberFilePicker(
                mimeType = MimeType.Pdf,
                onSuccess = component::addUris
            )

            FileReorderVerticalList(
                files = component.uris,
                onReorder = component::setUris,
                onNeedToAddFile = addFilesPicker::pickFile,
                onNeedToRemoveFileAt = component::removeAt
            )
        }
    )
}