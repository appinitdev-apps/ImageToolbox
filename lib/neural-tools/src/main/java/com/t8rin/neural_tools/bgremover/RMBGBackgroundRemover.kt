/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.neural_tools.bgremover

internal object RMBGBackgroundRemover : GenericBackgroundRemover(
    path = "onnx/bgremove/RMBG_1.4.ort",
    trainedSize = 1024
)