/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.neural_tools.bgremover

internal object U2NetFullBackgroundRemover : GenericBackgroundRemover(
    path = "onnx/bgremove/u2net.onnx",
    trainedSize = 320
)