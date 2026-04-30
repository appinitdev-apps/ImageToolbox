/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.watermarking.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.JAVA_FORMAT_SPECIFICATION
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Info
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ImageSelector
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField
import com.t8rin.imagetoolbox.feature.watermarking.domain.WatermarkParams
import com.t8rin.imagetoolbox.feature.watermarking.domain.WatermarkingType

@Composable
fun WatermarkDataSelector(
    value: WatermarkParams,
    onValueChange: (WatermarkParams) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        AnimatedContent(
            targetState = value.watermarkingType::class.qualifiedName,
            transitionSpec = { fadeIn() + slideInVertically() togetherWith fadeOut() + slideOutVertically() }
        ) { qualifiedName ->
            when (qualifiedName) {
                WatermarkingType.Image::class.qualifiedName -> {
                    val type = value.watermarkingType as? WatermarkingType.Image
                        ?: return@AnimatedContent

                    ImageSelector(
                        value = type.imageData,
                        subtitle = stringResource(id = R.string.watermarking_image_sub),
                        onValueChange = {
                            onValueChange(
                                value.copy(
                                    watermarkingType = type.copy(imageData = it)
                                )
                            )
                        },
                        modifier = modifier.fillMaxWidth()
                    )
                }

                WatermarkingType.Text::class.qualifiedName -> {
                    val type = value.watermarkingType as? WatermarkingType.Text
                        ?: return@AnimatedContent

                    RoundedTextField(
                        modifier = modifier
                            .container(
                                shape = MaterialTheme.shapes.large,
                                resultPadding = 8.dp
                            ),
                        value = type.text,
                        singleLine = false,
                        onValueChange = {
                            onValueChange(
                                value.copy(
                                    watermarkingType = type.copy(text = it)
                                )
                            )
                        },
                        label = {
                            Text(stringResource(R.string.text))
                        }
                    )
                }

                WatermarkingType.Stamp.Text::class.qualifiedName -> {
                    val type = value.watermarkingType as? WatermarkingType.Stamp.Text
                        ?: return@AnimatedContent

                    RoundedTextField(
                        modifier = modifier
                            .container(
                                shape = MaterialTheme.shapes.large,
                                resultPadding = 8.dp
                            ),
                        value = type.text,
                        singleLine = false,
                        onValueChange = {
                            onValueChange(
                                value.copy(
                                    watermarkingType = type.copy(text = it)
                                )
                            )
                        },
                        label = {
                            Text(stringResource(R.string.text))
                        }
                    )
                }

                WatermarkingType.Stamp.Time::class.qualifiedName -> {
                    val type = value.watermarkingType as? WatermarkingType.Stamp.Time
                        ?: return@AnimatedContent

                    RoundedTextField(
                        modifier = modifier
                            .container(
                                shape = MaterialTheme.shapes.large,
                                resultPadding = 8.dp
                            ),
                        value = type.format,
                        singleLine = false,
                        onValueChange = {
                            onValueChange(
                                value.copy(
                                    watermarkingType = type.copy(format = it)
                                )
                            )
                        },
                        label = {
                            Text(stringResource(R.string.format_pattern))
                        },
                        endIcon = {
                            val linkHandler = LocalUriHandler.current
                            EnhancedIconButton(
                                onClick = {
                                    linkHandler.openUri(JAVA_FORMAT_SPECIFICATION)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}