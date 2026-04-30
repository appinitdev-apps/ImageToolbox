/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.draw.presentation.components.element

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.filters.presentation.model.toUiFilter
import com.t8rin.imagetoolbox.core.filters.presentation.widget.AddFilterButton
import com.t8rin.imagetoolbox.core.filters.presentation.widget.FilterItem
import com.t8rin.imagetoolbox.core.filters.presentation.widget.FilterTemplateCreationSheetComponent
import com.t8rin.imagetoolbox.core.filters.presentation.widget.addFilters.AddFiltersSheet
import com.t8rin.imagetoolbox.core.filters.presentation.widget.addFilters.AddFiltersSheetComponent
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.AutoFixNormal
import com.t8rin.imagetoolbox.core.ui.theme.mixedContainer
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.other.InfoContainer
import com.t8rin.imagetoolbox.feature.draw.domain.DrawMode

@Composable
internal fun CustomPathEffectParamsSelector(
    value: DrawMode,
    onValueChange: (DrawMode) -> Unit,
    addFiltersSheetComponent: AddFiltersSheetComponent,
    filterTemplateCreationSheetComponent: FilterTemplateCreationSheetComponent
) {
    AnimatedVisibility(
        visible = value is DrawMode.PathEffect.Custom,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        val filter by remember(value) {
            derivedStateOf {
                (value as? DrawMode.PathEffect.Custom)?.filter?.toUiFilter()
            }
        }
        var showFilterSelection by rememberSaveable {
            mutableStateOf(false)
        }
        AnimatedContent(
            targetState = filter,
            contentKey = { it != null }
        ) { filter ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                if (filter != null) {
                    FilterItem(
                        filter = filter,
                        showDragHandle = false,
                        onRemove = {
                            onValueChange(
                                DrawMode.PathEffect.Custom()
                            )
                        },
                        onFilterChange = { value ->
                            onValueChange(
                                DrawMode.PathEffect.Custom(filter.copy(value))
                            )
                        },
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        shape = ShapeDefaults.default,
                        canHide = false,
                        onCreateTemplate = null
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    EnhancedButton(
                        containerColor = MaterialTheme.colorScheme.mixedContainer,
                        onClick = {
                            showFilterSelection = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AutoFixNormal,
                            contentDescription = stringResource(R.string.replace_filter)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(id = R.string.replace_filter))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                } else {
                    InfoContainer(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                        text = stringResource(R.string.pick_filter_info)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AddFilterButton(
                        onClick = {
                            showFilterSelection = true
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            AddFiltersSheet(
                component = addFiltersSheetComponent,
                filterTemplateCreationSheetComponent = filterTemplateCreationSheetComponent,
                visible = showFilterSelection,
                onVisibleChange = {
                    showFilterSelection = it
                },
                canAddTemplates = false,
                previewBitmap = null,
                onFilterPicked = {
                    onValueChange(
                        DrawMode.PathEffect.Custom(it.newInstance())
                    )
                },
                onFilterPickedWithParams = {
                    onValueChange(
                        DrawMode.PathEffect.Custom(it)
                    )
                }
            )
        }
    }
}