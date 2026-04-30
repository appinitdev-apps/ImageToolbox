/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.image.model.ImageScaleMode
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.FitScreen
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ScaleModeSelector
import com.t8rin.imagetoolbox.core.ui.widget.icon_shape.IconShapeContainer
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItemDefaults

@Composable
fun DefaultScaleModeSettingItem(
    onValueChange: (ImageScaleMode) -> Unit,
    shape: Shape = ShapeDefaults.top,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current
    ScaleModeSelector(
        modifier = modifier,
        shape = shape,
        backgroundColor = Color.Unspecified,
        value = settingsState.defaultImageScaleMode,
        onValueChange = onValueChange,
        titlePadding = PaddingValues(
            top = 12.dp,
            start = 12.dp,
            end = 12.dp
        ),
        titleArrangement = Arrangement.Start,
        enableItemsCardBackground = false,
        title = {
            IconShapeContainer(
                content = {
                    Icon(
                        imageVector = Icons.Outlined.FitScreen,
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.scale_mode),
                style = PreferenceItemDefaults.TitleFontStyle,
                modifier = Modifier.weight(1f, false)
            )
        }
    )
}