/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ascii_art.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.filters.presentation.widget.filterItem.AsciiParamsSelector
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Build
import com.t8rin.imagetoolbox.core.resources.icons.InvertColors
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import com.t8rin.imagetoolbox.core.ui.widget.text.TitleItem
import com.t8rin.imagetoolbox.feature.ascii_art.presentation.screenLogic.AsciiArtComponent

@Composable
internal fun AsciiArtControls(
    component: AsciiArtComponent
) {
    Column(
        modifier = Modifier
            .container(
                shape = ShapeDefaults.large,
                resultPadding = 0.dp
            )
    ) {
        TitleItem(
            text = stringResource(R.string.params),
            icon = Icons.Rounded.Build,
        )
        Box(
            modifier = Modifier
                .padding(
                    horizontal = 12.dp
                )
        ) {
            AsciiParamsSelector(
                value = component.asciiParams,
                onValueChange = { params ->
                    component.setGradient(params.gradient)
                    component.setFontSize(params.fontSize)
                },
                itemShapes = {
                    ShapeDefaults.byIndex(
                        index = it,
                        size = 3
                    )
                }
            )
        }
        Spacer(Modifier.height(4.dp))
        PreferenceRowSwitch(
            title = stringResource(R.string.invert_colors),
            subtitle = stringResource(R.string.invert_colors_ascii_sub),
            startIcon = Icons.Rounded.InvertColors,
            checked = component.isInvertImage,
            onClick = {
                component.toggleIsInvertImage()
            },
            shape = ShapeDefaults.bottom,
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        Spacer(Modifier.height(12.dp))
    }
}