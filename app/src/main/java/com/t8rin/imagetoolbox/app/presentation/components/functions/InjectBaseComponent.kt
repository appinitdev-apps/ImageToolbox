/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import com.t8rin.imagetoolbox.app.presentation.components.ImageToolboxApplication
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent

internal fun ImageToolboxApplication.injectBaseComponent() = BaseComponent.inject(keepAliveService)