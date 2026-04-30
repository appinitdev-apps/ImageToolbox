/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.palette_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import com.t8rin.imagetoolbox.core.resources.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.dynamic.theme.PaletteStyle
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.MiniEdit
import com.t8rin.imagetoolbox.core.resources.icons.Swatch
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedModalBottomSheet
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.enhancedFlingBehavior
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem
import com.t8rin.imagetoolbox.core.ui.widget.text.TitleItem
import com.t8rin.imagetoolbox.core.utils.appContext

@Composable
fun PaletteStyleSelection(
    onThemeStyleSelected: (PaletteStyle) -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    val settingsState = LocalSettingsState.current
    var showPaletteStyleSelectionSheet by rememberSaveable { mutableStateOf(false) }
    PreferenceItem(
        title = stringResource(R.string.palette_style),
        subtitle = remember(settingsState.themeStyle) {
            derivedStateOf {
                settingsState.themeStyle.getTitle(appContext)
            }
        }.value,
        shape = shape,
        containerColor = color,
        modifier = modifier,
        startIcon = Icons.Rounded.Swatch,
        endIcon = Icons.Rounded.MiniEdit,
        onClick = {
            showPaletteStyleSelectionSheet = true
        }
    )

    EnhancedModalBottomSheet(
        visible = showPaletteStyleSelectionSheet,
        onDismiss = {
            showPaletteStyleSelectionSheet = it
        },
        title = {
            TitleItem(
                text = stringResource(R.string.palette_style),
                icon = Icons.Rounded.Swatch
            )
        },
        confirmButton = {
            EnhancedButton(
                onClick = { showPaletteStyleSelectionSheet = false }
            ) {
                Text(text = stringResource(R.string.close))
            }
        },
        sheetContent = {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(250.dp),
                contentPadding = PaddingValues(16.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                flingBehavior = enhancedFlingBehavior()
            ) {
                items(PaletteStyle.entries) { style ->
                    PaletteStyleSelectionItem(
                        style = style,
                        onClick = {
                            onThemeStyleSelected(style)
                        }
                    )
                }
            }
        }
    )
}