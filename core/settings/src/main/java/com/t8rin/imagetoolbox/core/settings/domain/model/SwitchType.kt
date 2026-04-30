/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.settings.domain.model

sealed class SwitchType(val ordinal: Int) {

    data object MaterialYou : SwitchType(0)
    data object Compose : SwitchType(1)
    data object Pixel : SwitchType(2)
    data object Fluent : SwitchType(3)
    data object Cupertino : SwitchType(4)
    data object LiquidGlass : SwitchType(5)
    data object HyperOS : SwitchType(6)
    data object OneUI : SwitchType(7)

    companion object {
        fun fromInt(ordinal: Int) = when (ordinal) {
            1 -> Compose
            2 -> Pixel
            3 -> Fluent
            4 -> Cupertino
            5 -> LiquidGlass
            6 -> HyperOS
            7 -> OneUI
            else -> MaterialYou
        }

        val entries by lazy {
            listOf(
                MaterialYou, Compose, Pixel, Fluent, Cupertino, LiquidGlass, HyperOS, OneUI
            )
        }
    }

}