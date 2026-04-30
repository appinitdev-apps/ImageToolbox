/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.main.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.getStringLocalized
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.utils.appContext
import java.util.Locale

@Composable
internal fun filteredScreenListFor(
    screenSearchKeyword: String,
    selectedNavigationItem: Int,
    showScreenSearch: Boolean
): State<List<Screen>> {
    val settingsState = LocalSettingsState.current
    val canSearchScreens = settingsState.screensSearchEnabled

    val screenList by remember(settingsState.screenList) {
        derivedStateOf {
            settingsState.screenList.mapNotNull {
                Screen.entries.find { s -> s.id == it }
            }.takeIf { it.isNotEmpty() } ?: Screen.entries
        }
    }

    return remember(
        settingsState.groupOptionsByTypes,
        settingsState.favoriteScreenList,
        screenSearchKeyword,
        screenList,
        selectedNavigationItem,
        showScreenSearch
    ) {
        derivedStateOf {
            when {
                settingsState.groupOptionsByTypes && (screenSearchKeyword.isEmpty() && !showScreenSearch) -> {
                    Screen.typedEntries[selectedNavigationItem].entries
                }

                !settingsState.groupOptionsByTypes && (screenSearchKeyword.isEmpty() && !showScreenSearch) -> {
                    if (selectedNavigationItem == 0) {
                        screenList.filter {
                            it.id in settingsState.favoriteScreenList
                        }
                    } else screenList
                }

                else -> screenList
            }.let { screens ->
                if (screenSearchKeyword.isNotEmpty() && canSearchScreens) {
                    screens.filter {
                        val string =
                            appContext.getString(it.title) + " " + appContext.getString(it.subtitle)
                        val stringEn = appContext.getStringLocalized(it.title, Locale.ENGLISH)
                            .plus(" ")
                            .plus(appContext.getStringLocalized(it.subtitle, Locale.ENGLISH))
                        stringEn.contains(other = screenSearchKeyword, ignoreCase = true).or(
                            string.contains(other = screenSearchKeyword, ignoreCase = true)
                        )
                    }
                } else screens
            }
        }
    }
}