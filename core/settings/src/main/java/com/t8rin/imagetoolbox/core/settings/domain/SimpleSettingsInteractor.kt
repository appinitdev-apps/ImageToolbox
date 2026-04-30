/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.settings.domain

import com.t8rin.imagetoolbox.core.domain.model.ColorModel
import com.t8rin.imagetoolbox.core.settings.domain.model.OneTimeSaveLocation

interface SimpleSettingsInteractor {

    suspend fun toggleMagnifierEnabled()

    suspend fun setOneTimeSaveLocations(value: List<OneTimeSaveLocation>)

    suspend fun toggleRecentColor(
        color: ColorModel,
        forceExclude: Boolean = false
    )

    suspend fun toggleFavoriteColor(
        color: ColorModel,
        forceExclude: Boolean = true
    )

    fun isInstalledFromPlayStore(): Boolean

    suspend fun toggleSettingsGroupVisibility(
        key: Int,
        value: Boolean
    )

    suspend fun clearRecentColors()

    suspend fun updateFavoriteColors(
        colors: List<ColorModel>
    )

    suspend fun setBackgroundColorForNoAlphaFormats(
        color: ColorModel
    )

    suspend fun toggleCustomAsciiGradient(gradient: String)

    suspend fun toggleOverwriteFiles()

    suspend fun setSpotHealMode(mode: Int)

    suspend fun setBorderWidth(width: Float)

}