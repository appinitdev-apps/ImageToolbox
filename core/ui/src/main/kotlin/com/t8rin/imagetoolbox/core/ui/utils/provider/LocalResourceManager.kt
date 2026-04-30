/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.provider

import androidx.compose.runtime.compositionLocalOf
import com.t8rin.imagetoolbox.core.domain.resource.ResourceManager

val LocalResourceManager = compositionLocalOf<ResourceManager> { error("ResourceManager") }