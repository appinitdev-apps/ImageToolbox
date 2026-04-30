/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.image_cutting.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.utils.roundTo
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.BorderHorizontal
import com.t8rin.imagetoolbox.core.resources.icons.BorderVertical
import com.t8rin.imagetoolbox.core.resources.icons.SelectInverse
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedRangeSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import com.t8rin.imagetoolbox.image_cutting.domain.CutParams
import com.t8rin.imagetoolbox.image_cutting.domain.PivotPair

@Composable
internal fun CutParamsSelector(
    value: CutParams,
    onValueChange: (CutParams) -> Unit
) {
    val params by rememberUpdatedState(value)
    val layoutDirection = LocalLayoutDirection.current

    Column {
        EnhancedRangeSliderItem(
            value = params.vertical?.let { it.start..it.end } ?: 0f..1f,
            valueRange = 0f..1f,
            icon = Icons.Rounded.BorderVertical,
            title = stringResource(R.string.vertical_pivot_line),
            internalStateTransformation = {
                it.start.roundTo(3)..it.endInclusive.roundTo(3)
            },
            onValueChange = {
                onValueChange(
                    params.copy(
                        vertical = PivotPair(
                            start = it.start,
                            end = it.endInclusive,
                            isRtl = layoutDirection == LayoutDirection.Rtl
                        )
                    )
                )
            },
            additionalContent = {
                PreferenceRowSwitch(
                    title = stringResource(R.string.inverse_selection),
                    subtitle = stringResource(R.string.inverse_vertical_selection_sub),
                    startIcon = Icons.Rounded.SelectInverse,
                    checked = params.inverseVertical,
                    onClick = {
                        onValueChange(
                            params.copy(
                                inverseVertical = it
                            )
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                    shape = ShapeDefaults.small,
                    modifier = Modifier.padding(
                        start = 4.dp,
                        end = 4.dp,
                        bottom = 4.dp
                    )
                )
            }
        )
        Spacer(Modifier.height(8.dp))
        EnhancedRangeSliderItem(
            value = params.horizontal?.let { it.start..it.end } ?: 0f..1f,
            valueRange = 0f..1f,
            icon = Icons.Rounded.BorderHorizontal,
            title = stringResource(R.string.horizontal_pivot_line),
            internalStateTransformation = {
                it.start.roundTo(3)..it.endInclusive.roundTo(3)
            },
            onValueChange = {
                onValueChange(
                    params.copy(
                        horizontal = PivotPair(
                            start = it.start,
                            end = it.endInclusive,
                            isRtl = layoutDirection == LayoutDirection.Rtl
                        )
                    )
                )
            },
            additionalContent = {
                PreferenceRowSwitch(
                    title = stringResource(R.string.inverse_selection),
                    subtitle = stringResource(R.string.inverse_horizontal_selection_sub),
                    startIcon = Icons.Rounded.SelectInverse,
                    checked = params.inverseHorizontal,
                    onClick = {
                        onValueChange(
                            params.copy(
                                inverseHorizontal = it
                            )
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                    shape = ShapeDefaults.small,
                    modifier = Modifier.padding(
                        start = 4.dp,
                        end = 4.dp,
                        bottom = 4.dp
                    )
                )
            }
        )
    }
}