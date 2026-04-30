/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.image_stitch.domain

import com.t8rin.imagetoolbox.core.domain.image.model.BlendingMode

data class CombiningParams(
    val stitchMode: StitchMode = StitchMode.Horizontal,
    val spacing: Int = 0,
    val scaleSmallImagesToLarge: Boolean = false,
    val backgroundColor: Int = 0x00000000,
    val fadingEdgesMode: StitchFadeSide = StitchFadeSide.Start,
    val alignment: StitchAlignment = StitchAlignment.Start,
    val outputScale: Float = 0.5f,
    val blendingMode: BlendingMode = BlendingMode.SrcOver,
    val fadeStrength: Float = 1f
)