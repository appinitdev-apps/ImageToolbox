/* #AppInitDev -> Photo Utility Hub */



@file:Suppress("KotlinConstantConditions")

package com.t8rin.imagetoolbox.feature.settings.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.BuildConfig
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ReleaseAlert
import com.t8rin.imagetoolbox.core.resources.shapes.MaterialStarShape
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.theme.blend
import com.t8rin.imagetoolbox.core.ui.theme.outlineVariant
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppVersion
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.modifier.pulsate
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRow

@Composable
fun CurrentVersionCodeSettingItem(
    isUpdateAvailable: Boolean,
    onClick: () -> Unit,
    shape: Shape = ShapeDefaults.top,
    modifier: Modifier = Modifier.padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current
    PreferenceRow(
        shape = shape,
        modifier = Modifier
            .pulsate(
                enabled = isUpdateAvailable,
                range = 0.98f..1.02f
            )
            .then(modifier),
        title = stringResource(R.string.version),
        subtitle = remember {
            "$AppVersion (${BuildConfig.VERSION_CODE})"
        },
        startIcon = Icons.Outlined.ReleaseAlert,
        endContent = {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.version),
                tint = animateColorAsState(
                    if (settingsState.isNightMode) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.primary.blend(Color.Black)
                    }
                ).value,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(64.dp)
                    .container(
                        resultPadding = 0.dp,
                        color = animateColorAsState(
                            if (settingsState.isNightMode) {
                                MaterialTheme.colorScheme.secondaryContainer.blend(
                                    color = Color.Black,
                                    fraction = 0.3f
                                )
                            } else {
                                MaterialTheme.colorScheme.primaryContainer
                            }
                        ).value,
                        borderColor = MaterialTheme.colorScheme.outlineVariant(),
                        shape = MaterialStarShape
                    )
                    .scale(1.25f)
            )
        },
        onClick = onClick
    )
}