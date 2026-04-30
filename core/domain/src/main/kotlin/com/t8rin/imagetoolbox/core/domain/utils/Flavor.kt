/* #AppInitDev -> Photo Utility Hub */



@file:Suppress("SimplifyBooleanWithConstants", "KotlinConstantConditions")

package com.t8rin.imagetoolbox.core.domain.utils

import com.t8rin.imagetoolbox.core.resources.BuildConfig

object Flavor {
    fun isFoss() = BuildConfig.FLAVOR == "foss"
}