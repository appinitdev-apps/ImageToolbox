/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.provider

import androidx.compose.runtime.compositionLocalOf
import com.t8rin.imagetoolbox.core.domain.saving.KeepAliveService

val LocalKeepAliveService = compositionLocalOf<KeepAliveService> { error("No KeepAlive") }