/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.pages

import androidx.compose.animation.AnimatedContent
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
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumPreviewField
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.screenLogic.ChecksumToolsComponent

@Composable
internal fun ColumnScope.CalculateFromUriPage(
    component: ChecksumToolsComponent
) {
    val page = component.calculateFromUriPage

    FileSelector(
        value = page.uri?.toString(),
        onValueChange = component::setUri,
        subtitle = null
    )
    AnimatedContent(page.checksum) { checksum ->
        if (checksum.isNotEmpty()) {
            ChecksumPreviewField(
                value = checksum,
                onCopyText = Clipboard::copy,
                label = stringResource(R.string.checksum)
            )
        } else {
            InfoContainer(
                text = stringResource(R.string.pick_file_to_checksum),
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}