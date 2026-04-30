/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.settings.presentation.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.t8rin.dynamic.theme.ColorTuple
import com.t8rin.dynamic.theme.rememberAppColorTuple
import com.t8rin.imagetoolbox.core.settings.domain.SimpleSettingsInteractor
import com.t8rin.imagetoolbox.core.settings.presentation.model.EditPresetsController
import com.t8rin.imagetoolbox.core.settings.presentation.model.UiSettingsState

val LocalSettingsState =
    compositionLocalOf<UiSettingsState> { error("UiSettingsState not present") }

val LocalSimpleSettingsInteractor =
    compositionLocalOf<SimpleSettingsInteractor> { error("SimpleSettingInteractor not present") }

val LocalEditPresetsController =
    compositionLocalOf<EditPresetsController> { error("EditPresetsController not present") }

@Composable
fun rememberAppColorTuple(
    settingsState: UiSettingsState = LocalSettingsState.current
): ColorTuple = rememberAppColorTuple(
    defaultColorTuple = settingsState.appColorTuple,
    dynamicColor = settingsState.isDynamicColors,
    darkTheme = settingsState.isNightMode
)

@Composable
fun rememberEditPresetsController(
    initialVisibility: Boolean = false
) = rememberSaveable(initialVisibility, saver = EditPresetsController.Saver) {
    EditPresetsController(initialVisibility)
}