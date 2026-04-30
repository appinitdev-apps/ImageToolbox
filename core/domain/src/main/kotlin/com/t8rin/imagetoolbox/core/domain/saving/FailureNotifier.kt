/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.saving

fun interface FailureNotifier {
    fun send(error: Throwable)
}