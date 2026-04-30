/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.WEBLATE_LINK
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Translate
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem

@Composable
fun HelpTranslateSettingItem(
    shape: Shape = ShapeDefaults.center,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    val linkHandler = LocalUriHandler.current
    PreferenceItem(
        shape = shape,
        modifier = modifier,
        title = stringResource(R.string.help_translate),
        subtitle = stringResource(R.string.help_translate_sub),
        startIcon = Icons.Rounded.Translate,
        onClick = {
            linkHandler.openUri(WEBLATE_LINK)
        }
    )
}