/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.utils

import android.os.Build
import android.os.FileObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.io.File

@Suppress("DEPRECATION")
fun File.observeHasChanges(
    flags: Int = FileObserver.ALL_EVENTS
): Flow<Unit> = callbackFlow {
    val observer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        object : FileObserver(this@observeHasChanges, flags) {
            override fun onEvent(event: Int, path: String?) {
                trySend(Unit)
            }
        }
    } else {
        object : FileObserver(absolutePath, flags) {
            override fun onEvent(event: Int, path: String?) {
                trySend(Unit)
            }
        }
    }
    send(Unit)
    observer.startWatching()
    awaitClose { observer.stopWatching() }
}