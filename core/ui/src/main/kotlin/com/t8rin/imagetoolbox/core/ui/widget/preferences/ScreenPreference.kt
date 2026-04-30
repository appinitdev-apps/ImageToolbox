/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.preferences

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen

@Composable
internal fun ScreenPreference(
    screen: Screen,
    navigate: (Screen) -> Unit
) {
    val basePreference = @Composable {
        PreferenceItem(
            onClick = { navigate(screen) },
            startIcon = screen.icon,
            title = stringResource(screen.title),
            subtitle = stringResource(screen.subtitle),
            modifier = Modifier.fillMaxWidth()
        )
    }
    when (screen) {
        is Screen.GifTools -> {
            if (screen.type != null) {
                PreferenceItem(
                    onClick = { navigate(screen) },
                    startIcon = screen.type.icon,
                    title = stringResource(screen.type.title),
                    subtitle = stringResource(screen.type.subtitle),
                    modifier = Modifier.fillMaxWidth()
                )
            } else basePreference()
        }

        is Screen.Filter -> {
            if (screen.type != null) {
                PreferenceItem(
                    onClick = { navigate(screen) },
                    startIcon = screen.type.icon,
                    title = stringResource(screen.type.title),
                    subtitle = stringResource(screen.type.subtitle),
                    modifier = Modifier.fillMaxWidth()
                )
            } else basePreference()
        }

        is Screen.ApngTools -> {
            if (screen.type != null) {
                PreferenceItem(
                    onClick = { navigate(screen) },
                    startIcon = screen.type.icon,
                    title = stringResource(screen.type.title),
                    subtitle = stringResource(screen.type.subtitle),
                    modifier = Modifier.fillMaxWidth()
                )
            } else basePreference()
        }

        is Screen.JxlTools -> {
            if (screen.type != null) {
                PreferenceItem(
                    onClick = { navigate(screen) },
                    startIcon = screen.type.icon,
                    title = stringResource(screen.type.title),
                    subtitle = stringResource(screen.type.subtitle),
                    modifier = Modifier.fillMaxWidth()
                )
            } else basePreference()
        }

        is Screen.WebpTools -> {
            if (screen.type != null) {
                PreferenceItem(
                    onClick = { navigate(screen) },
                    startIcon = screen.type.icon,
                    title = stringResource(screen.type.title),
                    subtitle = stringResource(screen.type.subtitle),
                    modifier = Modifier.fillMaxWidth()
                )
            } else basePreference()
        }

        is Screen.RecognizeText -> {
            if (screen.type != null) {
                PreferenceItem(
                    onClick = { navigate(screen) },
                    startIcon = screen.type.icon,
                    title = stringResource(screen.type.title),
                    subtitle = stringResource(screen.type.subtitle),
                    modifier = Modifier.fillMaxWidth()
                )
            } else basePreference()
        }

        else -> basePreference()
    }
}