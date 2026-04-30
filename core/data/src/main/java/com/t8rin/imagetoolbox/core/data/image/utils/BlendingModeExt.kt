/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image.utils

import android.graphics.Paint
import android.graphics.PorterDuffXfermode
import android.os.Build
import androidx.annotation.RequiresApi
import com.t8rin.imagetoolbox.core.domain.image.model.BlendingMode
import android.graphics.BlendMode as AndroidBlendMode
import android.graphics.PorterDuff.Mode as PorterDuffMode


fun BlendingMode.toPorterDuffMode(): PorterDuffMode = when (this) {
    BlendingMode.Clear -> PorterDuffMode.CLEAR
    BlendingMode.Src -> PorterDuffMode.SRC
    BlendingMode.Dst -> PorterDuffMode.DST
    BlendingMode.SrcOver -> PorterDuffMode.SRC_OVER
    BlendingMode.DstOver -> PorterDuffMode.DST_OVER
    BlendingMode.SrcIn -> PorterDuffMode.SRC_IN
    BlendingMode.DstIn -> PorterDuffMode.DST_IN
    BlendingMode.SrcOut -> PorterDuffMode.SRC_OUT
    BlendingMode.DstOut -> PorterDuffMode.DST_OUT
    BlendingMode.SrcAtop -> PorterDuffMode.SRC_ATOP
    BlendingMode.DstAtop -> PorterDuffMode.DST_ATOP
    BlendingMode.Xor -> PorterDuffMode.XOR
    BlendingMode.Plus -> PorterDuffMode.ADD
    BlendingMode.Screen -> PorterDuffMode.SCREEN
    BlendingMode.Overlay -> PorterDuffMode.OVERLAY
    BlendingMode.Darken -> PorterDuffMode.DARKEN
    BlendingMode.Lighten -> PorterDuffMode.LIGHTEN
    BlendingMode.Modulate -> {
        // b/73224934 Android PorterDuff Multiply maps to Skia Modulate
        PorterDuffMode.MULTIPLY
    }
    // Always return SRC_OVER as the default if there is no valid alternative
    else -> PorterDuffMode.SRC_OVER
}

/**
 * Convert the domain [BlendingMode] to the underlying Android platform [AndroidBlendMode]
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun BlendingMode.toAndroidBlendMode(): AndroidBlendMode = when (this) {
    BlendingMode.Clear -> AndroidBlendMode.CLEAR
    BlendingMode.Src -> AndroidBlendMode.SRC
    BlendingMode.Dst -> AndroidBlendMode.DST
    BlendingMode.SrcOver -> AndroidBlendMode.SRC_OVER
    BlendingMode.DstOver -> AndroidBlendMode.DST_OVER
    BlendingMode.SrcIn -> AndroidBlendMode.SRC_IN
    BlendingMode.DstIn -> AndroidBlendMode.DST_IN
    BlendingMode.SrcOut -> AndroidBlendMode.SRC_OUT
    BlendingMode.DstOut -> AndroidBlendMode.DST_OUT
    BlendingMode.SrcAtop -> AndroidBlendMode.SRC_ATOP
    BlendingMode.DstAtop -> AndroidBlendMode.DST_ATOP
    BlendingMode.Xor -> AndroidBlendMode.XOR
    BlendingMode.Plus -> AndroidBlendMode.PLUS
    BlendingMode.Modulate -> AndroidBlendMode.MODULATE
    BlendingMode.Screen -> AndroidBlendMode.SCREEN
    BlendingMode.Overlay -> AndroidBlendMode.OVERLAY
    BlendingMode.Darken -> AndroidBlendMode.DARKEN
    BlendingMode.Lighten -> AndroidBlendMode.LIGHTEN
    BlendingMode.ColorDodge -> AndroidBlendMode.COLOR_DODGE
    BlendingMode.ColorBurn -> AndroidBlendMode.COLOR_BURN
    BlendingMode.Hardlight -> AndroidBlendMode.HARD_LIGHT
    BlendingMode.Softlight -> AndroidBlendMode.SOFT_LIGHT
    BlendingMode.Difference -> AndroidBlendMode.DIFFERENCE
    BlendingMode.Exclusion -> AndroidBlendMode.EXCLUSION
    BlendingMode.Multiply -> AndroidBlendMode.MULTIPLY
    BlendingMode.Hue -> AndroidBlendMode.HUE
    BlendingMode.Saturation -> AndroidBlendMode.SATURATION
    BlendingMode.Color -> AndroidBlendMode.COLOR
    BlendingMode.Luminosity -> AndroidBlendMode.LUMINOSITY
    // Always return SRC_OVER as the default if there is no valid alternative
    else -> AndroidBlendMode.SRC_OVER
}

fun BlendingMode.toPaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        blendMode = toAndroidBlendMode()
    } else {
        xfermode = PorterDuffXfermode(toPorterDuffMode())
    }
}