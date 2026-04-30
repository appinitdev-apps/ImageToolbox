/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Exif
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch

@Composable
fun SaveExifWidget(
    checked: Boolean,
    imageFormat: ImageFormat,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified
) {
    val settingsState = LocalSettingsState.current
    LaunchedEffect(Unit) {
        onCheckedChange(settingsState.exifWidgetInitialState)
    }
    PreferenceRowSwitch(
        modifier = modifier,
        title = stringResource(R.string.keep_exif),
        subtitle = if (imageFormat.canWriteExif) {
            stringResource(R.string.keep_exif_sub)
        } else {
            stringResource(
                R.string.image_exif_warning,
                imageFormat.title
            )
        },
        checked = checked,
        enabled = imageFormat.canWriteExif,
        shape = ShapeDefaults.extraLarge,
        containerColor = backgroundColor,
        onClick = onCheckedChange,
        startIcon = Icons.Outlined.Exif
    )
}