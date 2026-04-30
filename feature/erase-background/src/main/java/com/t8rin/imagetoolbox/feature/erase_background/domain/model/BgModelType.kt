/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.erase_background.domain.model

import com.t8rin.imagetoolbox.core.domain.utils.Flavor

enum class BgModelType(
    val title: String
) {
    MlKit("MlKit"),
    U2NetP("U2NetP"),
    U2Net("U2Net"),
    RMBG("RMBG"),
    InSPyReNet("InSPyReNet"),
    BiRefNetTiny("BiRefNet"),
    ISNet("ISNet");

    companion object {
        val Default = if (Flavor.isFoss()) U2NetP else MlKit
    }
}