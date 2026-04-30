/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image.utils

import coil3.gif.repeatCount
import coil3.request.ImageRequest
import com.awxkee.jxlcoder.coil.enableJxlAnimation
import com.github.awxkee.avifcoil.decoder.animation.enableAvifAnimation
import com.t8rin.imagetoolbox.core.data.coil.UpscaleSvgDecoder

fun ImageRequest.Builder.static() = repeatCount(0)
    .enableAvifAnimation(false)
    .enableJxlAnimation(false)
    .decoderFactory(UpscaleSvgDecoder.Factory())