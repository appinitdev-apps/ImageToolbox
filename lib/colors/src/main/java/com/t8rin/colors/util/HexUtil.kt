/* #AppInitDev -> Photo Utility Hub */



@file:Suppress("unused")

package com.t8rin.colors.util

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

object HexUtil {
    /*
    HEX-ColorInt Conversion
 */
    fun hexToColorInt(colorString: String): Int {
        val completeColorString = if (colorString.first() == '#') colorString else "#$colorString"
        return completeColorString.toColorInt()
    }

    /*
        HEX-RGB Conversion
     */
    fun hexToRGB(colorString: String): IntArray {
        val colorInt = hexToColorInt(colorString)
        return ColorUtil.colorIntToRGBArray(colorInt)
    }

    fun hexToRGB(colorString: String, rgbIn: IntArray) {
        val colorInt = hexToColorInt(colorString)
        ColorUtil.colorIntToRGBArray(colorInt, rgbIn)
    }

    fun hexToARGB(colorString: String): IntArray {
        val colorInt = hexToColorInt(colorString)
        return ColorUtil.colorIntToARGBArray(colorInt)
    }

    fun hexToARGB(colorString: String, argbIn: IntArray) {
        val colorInt = hexToColorInt(colorString)
        ColorUtil.colorIntToARGBArray(colorInt, argbIn)
    }


    fun hexToColor(colorString: String): Color {
        return Color(hexToColorInt(colorString))
    }

}