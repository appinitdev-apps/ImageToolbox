/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.presentation.components

import androidx.compose.foundation.layout.RowScope
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.AutoFixHigh
import com.t8rin.imagetoolbox.core.resources.icons.Eyedropper
import com.t8rin.imagetoolbox.core.resources.icons.Texture
import com.t8rin.imagetoolbox.core.ui.theme.mixedContainer
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.buttons.ZoomButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.core.ui.widget.saver.ColorSaver
import com.t8rin.imagetoolbox.core.ui.widget.sheets.ZoomModalSheet
import com.t8rin.imagetoolbox.feature.filters.presentation.screenLogic.FiltersComponent
import com.t8rin.imagetoolbox.feature.pick_color.presentation.components.PickColorFromImageSheet

@Composable
internal fun RowScope.FiltersContentTopAppBarActions(
    component: FiltersComponent,
    actions: @Composable RowScope.() -> Unit
) {
    val isPortrait by isPortraitOrientationAsState()

    if (component.previewBitmap != null) {
        var showColorPicker by rememberSaveable { mutableStateOf(false) }
        var tempColor by rememberSaveable(
            showColorPicker,
            stateSaver = ColorSaver
        ) { mutableStateOf(Color.Black) }

        EnhancedIconButton(
            onClick = {
                showColorPicker = true
            },
            enabled = component.previewBitmap != null
        ) {
            Icon(
                imageVector = Icons.Outlined.Eyedropper,
                contentDescription = stringResource(R.string.pipette)
            )
        }
        PickColorFromImageSheet(
            visible = showColorPicker,
            onDismiss = {
                showColorPicker = false
            },
            bitmap = component.previewBitmap,
            onColorChange = { tempColor = it },
            color = tempColor
        )

        var showZoomSheet by rememberSaveable { mutableStateOf(false) }
        ZoomButton(
            onClick = { showZoomSheet = true },
            visible = component.bitmap != null,
        )
        ZoomModalSheet(
            data = component.previewBitmap,
            visible = showZoomSheet,
            onDismiss = {
                showZoomSheet = false
            }
        )
    }
    if (component.bitmap == null) {
        TopAppBarEmoji()
    } else {
        if (isPortrait) {
            when (component.filterType) {
                is Screen.Filter.Type.Basic -> {
                    EnhancedIconButton(
                        containerColor = MaterialTheme.colorScheme.mixedContainer,
                        onClick = component::showAddFiltersSheet
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AutoFixHigh,
                            contentDescription = stringResource(R.string.add_filter)
                        )
                    }
                }

                is Screen.Filter.Type.Masking -> {
                    EnhancedIconButton(
                        containerColor = MaterialTheme.colorScheme.mixedContainer,
                        onClick = component::showAddFiltersSheet
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Texture,
                            contentDescription = stringResource(R.string.add_mask)
                        )
                    }
                }

                null -> Unit
            }
        } else {
            actions()
        }
    }
}