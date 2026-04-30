/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pick_color.presentation.components

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.t8rin.colors.parser.ColorNameParser
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.AddPhotoAlt
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedFloatingActionButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.drawHorizontalStroke

@Composable
internal fun PickColorFromImageBottomAppBar(
    bitmap: Bitmap?,
    isPortrait: Boolean,
    switch: @Composable () -> Unit,
    onOneTimePickImage: () -> Unit,
    onPickImage: () -> Unit,
    color: Color,
) {
    AnimatedVisibility(
        visible = bitmap != null && isPortrait,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        val text by remember(color) {
            derivedStateOf {
                ColorNameParser.parseColorName(color)
            }
        }

        BottomAppBar(
            modifier = Modifier
                .drawHorizontalStroke(true),
            actions = {
                switch()
                Spacer(Modifier.width(16.dp))
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    text = text,
                    textAlign = TextAlign.Center
                )
            },
            floatingActionButton = {
                EnhancedFloatingActionButton(
                    onClick = onPickImage,
                    onLongClick = onOneTimePickImage
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AddPhotoAlt,
                        contentDescription = stringResource(R.string.pick_image_alt)
                    )
                }
            }
        )
    }
}