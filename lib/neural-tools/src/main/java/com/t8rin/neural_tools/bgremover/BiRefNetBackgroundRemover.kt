/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.neural_tools.bgremover

internal object BiRefNetBackgroundRemover : GenericBackgroundRemover(
    path = "birefnet_fp16.ort",
    trainedSize = 1024
)