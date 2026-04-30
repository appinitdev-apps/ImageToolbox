/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.neural_tools.bgremover

internal object ISNetBackgroundRemover : GenericBackgroundRemover(
    path = "isnet-general-use.onnx",
    trainedSize = 1024
)