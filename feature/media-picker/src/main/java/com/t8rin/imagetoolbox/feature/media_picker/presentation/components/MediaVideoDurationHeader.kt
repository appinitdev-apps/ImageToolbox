/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.media_picker.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.icons.PlayCircle
import com.t8rin.imagetoolbox.core.ui.theme.White
import com.t8rin.imagetoolbox.core.ui.widget.modifier.advancedShadow
import com.t8rin.imagetoolbox.feature.media_picker.data.utils.formatMinSec
import com.t8rin.imagetoolbox.feature.media_picker.domain.model.Media

@Composable
fun MediaVideoDurationHeader(
    modifier: Modifier = Modifier,
    media: Media
) {
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .advancedShadow(
                cornersRadius = 8.dp,
                shadowBlurRadius = 6.dp,
                alpha = 0.3f
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = media.duration.formatMinSec(),
            style = MaterialTheme.typography.labelSmall,
            color = White
        )
        Spacer(modifier = Modifier.size(2.dp))
        Icon(
            modifier = Modifier
                .size(16.dp)
                .advancedShadow(
                    cornersRadius = 2.dp,
                    shadowBlurRadius = 6.dp,
                    alpha = 0.1f,
                    offsetY = (-2).dp
                ),
            imageVector = Icons.Rounded.PlayCircle,
            tint = White,
            contentDescription = "Video"
        )
    }
}