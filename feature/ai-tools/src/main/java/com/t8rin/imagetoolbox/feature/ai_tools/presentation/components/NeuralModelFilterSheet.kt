/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.utils.ListUtils.toggle
import com.t8rin.imagetoolbox.core.domain.utils.ListUtils.toggleByClass
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Cancel
import com.t8rin.imagetoolbox.core.resources.icons.FilterAlt
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedBottomSheetDefaults
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedModalBottomSheet
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.enhancedVerticalScroll
import com.t8rin.imagetoolbox.core.ui.widget.modifier.clearFocusOnTap
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField
import com.t8rin.imagetoolbox.core.ui.widget.text.TitleItem
import com.t8rin.imagetoolbox.feature.ai_tools.domain.model.NeuralModel

@Composable
internal fun NeuralModelFilterSheet(
    visible: Boolean,
    onDismiss: (Boolean) -> Unit,
    typeFilters: List<NeuralModel.Type>,
    speedFilters: List<NeuralModel.Speed>,
    keywordFilter: String,
    onTypeFiltersChange: (List<NeuralModel.Type>) -> Unit,
    onSpeedFiltersChange: (List<NeuralModel.Speed>) -> Unit,
    onKeywordFilterChange: (String) -> Unit
) {
    EnhancedModalBottomSheet(
        visible = visible,
        onDismiss = onDismiss,
        confirmButton = {
            EnhancedButton(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = { onDismiss(false) }
            ) {
                Text(stringResource(R.string.close))
            }
        },
        title = {
            TitleItem(
                text = stringResource(id = R.string.filter),
                icon = Icons.Rounded.FilterAlt
            )
        }
    ) {
        Column(
            modifier = Modifier
                .enhancedVerticalScroll(rememberScrollState())
                .padding(16.dp)
                .clearFocusOnTap(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .container(
                        shape = MaterialTheme.shapes.large,
                        resultPadding = 8.dp,
                        color = EnhancedBottomSheetDefaults.contentContainerColor
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleItem(
                    text = stringResource(R.string.type),
                    modifier = Modifier
                        .padding(
                            start = 4.dp,
                            top = 4.dp,
                            end = 4.dp
                        )
                )
                Spacer(Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    NeuralModel.Type.entries.forEach { type ->
                        NeuralModelTypeBadge(
                            type = type,
                            isInverted = type in typeFilters,
                            onClick = {
                                onTypeFiltersChange(typeFilters.toggle(type))
                            },
                            height = 36.dp,
                            endPadding = 12.dp,
                            startPadding = 6.dp,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .container(
                        shape = MaterialTheme.shapes.large,
                        resultPadding = 8.dp,
                        color = EnhancedBottomSheetDefaults.contentContainerColor
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleItem(
                    text = stringResource(R.string.speed),
                    modifier = Modifier
                        .padding(
                            start = 4.dp,
                            top = 4.dp,
                            end = 4.dp
                        )
                )
                Spacer(Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    NeuralModel.Speed.entries.forEach { speed ->
                        NeuralModelSpeedBadge(
                            speed = speed,
                            isInverted = speedFilters.any { it::class.isInstance(speed) },
                            onClick = {
                                onSpeedFiltersChange(speedFilters.toggleByClass(speed))
                            },
                            height = 36.dp,
                            endPadding = 12.dp,
                            startPadding = 6.dp,
                            style = MaterialTheme.typography.labelMedium,
                            showTitle = true
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            RoundedTextField(
                value = keywordFilter,
                onValueChange = onKeywordFilterChange,
                label = stringResource(R.string.keyword),
                modifier = Modifier
                    .fillMaxWidth()
                    .container(
                        shape = MaterialTheme.shapes.large,
                        resultPadding = 8.dp,
                        color = EnhancedBottomSheetDefaults.contentContainerColor
                    ),
                singleLine = false,
                endIcon = {
                    AnimatedVisibility(keywordFilter.isNotBlank()) {
                        EnhancedIconButton(
                            onClick = { onKeywordFilterChange("") },
                            modifier = Modifier.padding(end = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Cancel,
                                contentDescription = stringResource(R.string.cancel)
                            )
                        }
                    }
                },
                maxLines = 4
            )
        }
    }
}