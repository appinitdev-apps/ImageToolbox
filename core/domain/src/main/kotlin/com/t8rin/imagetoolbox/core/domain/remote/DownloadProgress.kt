/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.remote

data class DownloadProgress(
    val currentPercent: Float,
    val currentTotalSize: Long
)