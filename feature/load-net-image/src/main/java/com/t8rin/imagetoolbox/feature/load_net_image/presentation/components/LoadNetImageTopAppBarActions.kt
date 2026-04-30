/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.load_net_image.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Close
import com.t8rin.imagetoolbox.core.resources.icons.SelectAll
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.feature.load_net_image.presentation.screenLogic.LoadNetImageComponent

@Composable
internal fun RowScope.LoadNetImageTopAppBarActions(
    component: LoadNetImageComponent
) {
    if (component.bitmap == null) {
        TopAppBarEmoji()
    } else {
        AnimatedVisibility(component.parsedImages.size > 1) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val pagesSize by remember(component.imageFrames, component.parsedImages) {
                    derivedStateOf {
                        component.imageFrames.getFramePositions(component.parsedImages.size).size
                    }
                }
                AnimatedVisibility(
                    visible = pagesSize != component.parsedImages.size,
                    enter = fadeIn() + scaleIn() + expandHorizontally(),
                    exit = fadeOut() + scaleOut() + shrinkHorizontally()
                ) {
                    EnhancedIconButton(
                        onClick = component::selectAllImages
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.SelectAll,
                            contentDescription = "Select All"
                        )
                    }
                }
                AnimatedVisibility(
                    modifier = Modifier
                        .padding(8.dp)
                        .container(
                            shape = ShapeDefaults.circle,
                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                            resultPadding = 0.dp
                        ),
                    visible = pagesSize != 0
                ) {
                    Row(
                        modifier = Modifier.padding(start = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        pagesSize.takeIf { it != 0 }?.let {
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = it.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        EnhancedIconButton(
                            onClick = component::clearImagesSelection
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = stringResource(R.string.close)
                            )
                        }
                    }
                }
            }
        }
    }
}