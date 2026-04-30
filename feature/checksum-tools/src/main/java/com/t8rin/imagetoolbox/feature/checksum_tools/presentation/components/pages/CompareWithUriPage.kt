/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.FileSelector
import com.t8rin.imagetoolbox.core.ui.widget.other.InfoContainer
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumEnterField
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumPreviewField
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumResultCard
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.screenLogic.ChecksumToolsComponent

@Composable
internal fun ColumnScope.CompareWithUriPage(
    component: ChecksumToolsComponent,
) {
    val page = component.compareWithUriPage

    FileSelector(
        value = page.uri?.toString(),
        onValueChange = component::setDataForComparison,
        subtitle = null
    )
    AnimatedContent(page.checksum) { checksum ->
        if (checksum.isNotEmpty()) {
            ChecksumPreviewField(
                value = checksum,
                onCopyText = Clipboard::copy
            )
        } else {
            InfoContainer(
                text = stringResource(R.string.pick_file_to_checksum),
                modifier = Modifier.padding(8.dp),
            )
        }
    }

    ChecksumEnterField(
        value = page.targetChecksum,
        onValueChange = {
            component.setDataForComparison(
                targetChecksum = it
            )
        }
    )

    AnimatedVisibility(
        page.targetChecksum.isNotEmpty() && !page.uri?.toString()
            .isNullOrEmpty()
    ) {
        ChecksumResultCard(page.isCorrect)
    }
}