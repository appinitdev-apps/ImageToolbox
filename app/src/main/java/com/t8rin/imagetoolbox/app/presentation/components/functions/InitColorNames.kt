/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import com.t8rin.colors.parser.ColorNameParser
import com.t8rin.imagetoolbox.app.presentation.components.ImageToolboxApplication
import kotlinx.coroutines.launch

internal fun ImageToolboxApplication.initColorNames() =
    appScope.launch { ColorNameParser.init(this@initColorNames) }