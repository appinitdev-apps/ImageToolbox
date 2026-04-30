/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.neural_tools.bgremover

internal object InSPyReNetBackgroundRemover : GenericBackgroundRemover(
    path = "onnx/bgremove/inspyrenet.onnx",
    trainedSize = 1024
)