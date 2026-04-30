/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.StarSticky
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedChip
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.enhancedFlingBehavior
import com.t8rin.imagetoolbox.core.ui.widget.modifier.fadingEdges
import com.t8rin.imagetoolbox.feature.markup_layers.domain.ShapeMode
import com.t8rin.imagetoolbox.feature.markup_layers.domain.defaultMode
import com.t8rin.imagetoolbox.feature.markup_layers.presentation.components.model.icon

@Composable
internal fun AddShapeLayerDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    onShapePicked: (ShapeMode) -> Unit
) {
    var selectedKindName by rememberSaveable(visible) {
        mutableStateOf<String?>(null)
    }

    val selectedKind = selectedKindName?.let { raw ->
        ShapeMode.Kind.entries.firstOrNull { it.name == raw }
    }

    EnhancedAlertDialog(
        visible = visible,
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Outlined.StarSticky,
                contentDescription = null
            )
        },
        title = {
            Text(stringResource(R.string.shape))
        },
        text = {
            val state = rememberScrollState()

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterVertically
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        state = state,
                        flingBehavior = enhancedFlingBehavior()
                    )
                    .fadingEdges(
                        scrollableState = state,
                        isVertical = true
                    )
            ) {
                ShapeMode.Kind.entries.forEach { kind ->
                    EnhancedChip(
                        selected = selectedKind == kind,
                        onClick = {
                            selectedKindName = kind.name
                        },
                        selectedColor = MaterialTheme.colorScheme.tertiaryContainer,
                        unselectedColor = MaterialTheme.colorScheme.surface,
                        contentPadding = PaddingValues(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ),
                        label = {
                            Icon(
                                imageVector = kind.icon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        },
        dismissButton = {
            EnhancedButton(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.close))
            }
        },
        confirmButton = {
            EnhancedButton(
                enabled = selectedKind != null,
                onClick = {
                    selectedKind?.let {
                        onShapePicked(it.defaultMode())
                    }
                    onDismiss()
                }
            ) {
                Text(stringResource(R.string.add))
            }
        }
    )
}