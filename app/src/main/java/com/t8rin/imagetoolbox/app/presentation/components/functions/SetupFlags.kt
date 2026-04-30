/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import androidx.compose.foundation.ComposeFoundationFlags.isPausableCompositionInPrefetchEnabled
import androidx.compose.material3.ComposeMaterial3Flags.isCheckboxStylingFixEnabled
import com.arkivanov.decompose.DecomposeSettings

internal fun setupFlags() {
    isCheckboxStylingFixEnabled = true
    DecomposeSettings.update { it.copy(duplicateConfigurationsEnabled = true) }
    isPausableCompositionInPrefetchEnabled = true
}