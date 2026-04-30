/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.neural_tools.bgremover

internal object BiRefNetTinyBackgroundRemover : GenericBackgroundRemover(
    path = "birefnet_swin_tiny.ort",
    trainedSize = 1024
)