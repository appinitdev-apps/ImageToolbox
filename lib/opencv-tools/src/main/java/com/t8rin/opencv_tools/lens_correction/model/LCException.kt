/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.opencv_tools.lens_correction.model

sealed class LCException(message: String) : Throwable(message) {
    class MissingFisheyeParams : LCException("No fisheye_params in JSON")
    class InvalidMatrixSize : LCException("Incorrect camera_matrix size (pass 3x3)")
    class InvalidCalibDimensions : LCException("Invalid calibration dimensions")
    class InvalidDistortionCoeffs : LCException("Bad distortion coefficients (pass only 4)")
}