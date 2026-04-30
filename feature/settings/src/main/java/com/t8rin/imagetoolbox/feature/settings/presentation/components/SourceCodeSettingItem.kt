/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.APP_GITHUB_LINK
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Github
import com.t8rin.imagetoolbox.core.ui.theme.blend
import com.t8rin.imagetoolbox.core.ui.widget.icon_shape.LocalIconShapeContainerColor
import com.t8rin.imagetoolbox.core.ui.widget.icon_shape.LocalIconShapeContentColor
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem


@Composable
fun SourceCodeSettingItem(
    modifier: Modifier = Modifier.padding(horizontal = 8.dp),
    shape: Shape = ShapeDefaults.bottom,
) {
    val linkHandler = LocalUriHandler.current

    CompositionLocalProvider(
        LocalIconShapeContentColor provides MaterialTheme.colorScheme.onTertiaryContainer,
        LocalIconShapeContainerColor provides MaterialTheme.colorScheme.tertiaryContainer.blend(
            color = MaterialTheme.colorScheme.tertiary,
            fraction = 0.1f
        )
    ) {
        PreferenceItem(
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            shape = shape,
            onClick = {
                linkHandler.openUri(APP_GITHUB_LINK)
            },
            startIcon = Icons.Rounded.Github,
            title = stringResource(R.string.check_source_code),
            subtitle = stringResource(R.string.check_source_code_sub),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(0.7f),
            modifier = modifier,
            overrideIconShapeContentColor = true
        )
    }
}
