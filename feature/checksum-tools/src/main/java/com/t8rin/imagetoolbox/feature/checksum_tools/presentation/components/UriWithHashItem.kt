/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.t8rin.imagetoolbox.core.resources.Icons
import com.t8rin.imagetoolbox.core.resources.icons.FilePresent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.shapes.CloverShape
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.rememberFilename
import com.t8rin.imagetoolbox.core.ui.utils.helper.ImageUtils.rememberHumanFileSize
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItemOverload

@Composable
internal fun UriWithHashItem(
    uriWithHash: UriWithHash,
    onCopyText: (String) -> Unit
) {
    val (uri, checksum) = uriWithHash

    val filename = rememberFilename(uri) ?: stringResource(R.string.filename)

    val fileSize = rememberHumanFileSize(uri)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PreferenceItemOverload(
            title = filename,
            subtitle = fileSize,
            startIcon = {
                Icon(
                    imageVector = Icons.Outlined.FilePresent,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CloverShape)
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer.copy(
                                0.5f
                            )
                        )
                        .padding(8.dp)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            drawStartIconContainer = false
        )

        ChecksumPreviewField(
            value = checksum,
            onCopyText = onCopyText
        )
    }
}