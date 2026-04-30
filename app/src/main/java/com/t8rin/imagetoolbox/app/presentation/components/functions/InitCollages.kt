/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import com.t8rin.collages.public.CollageConstants
import com.t8rin.imagetoolbox.core.data.image.utils.static

fun initCollages() =
    CollageConstants.requestMapper { static() }