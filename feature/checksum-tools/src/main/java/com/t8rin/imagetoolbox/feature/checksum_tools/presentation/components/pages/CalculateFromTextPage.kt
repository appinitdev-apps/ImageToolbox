/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.widget.other.InfoContainer
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumEnterField
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumPreviewField
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.screenLogic.ChecksumToolsComponent

@Composable
internal fun ColumnScope.CalculateFromTextPage(
    component: ChecksumToolsComponent
) {
    val page = component.calculateFromTextPage

    var text by remember {
        mutableStateOf(page.text)
    }

    ChecksumEnterField(
        value = text,
        onValueChange = {
            text = it
            component.setText(it)
        },
        label = stringResource(R.string.text)
    )

    ChecksumPreviewField(
        value = page.checksum,
        onCopyText = Clipboard::copy,
        label = stringResource(R.string.checksum)
    )

    AnimatedVisibility(page.checksum.isEmpty()) {
        InfoContainer(
            text = stringResource(R.string.enter_text_to_checksum),
            modifier = Modifier.padding(8.dp),
        )
    }
}