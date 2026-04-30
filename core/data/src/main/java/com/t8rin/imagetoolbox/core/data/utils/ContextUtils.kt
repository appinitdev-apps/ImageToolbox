/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.compose.ui.unit.Density
import androidx.core.content.ContextCompat
import com.t8rin.imagetoolbox.core.domain.utils.FileMode
import kotlinx.coroutines.coroutineScope
import java.io.OutputStream

fun Context.isExternalStorageWritable(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) true
    else ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.openWriteableStream(
    uri: Uri?,
    onFailure: (Throwable) -> Unit = {}
): OutputStream? = uri?.let {
    runCatching {
        openOutputStream(
            uri = uri,
            mode = FileMode.ReadWrite
        )
    }.getOrElse {
        runCatching {
            openOutputStream(
                uri = uri,
                mode = FileMode.Write
            )
        }.onFailure(onFailure).getOrNull()
    }
}

fun Context.openFileDescriptor(uri: Uri, mode: FileMode = FileMode.Read) =
    contentResolver.openFileDescriptor(uri, mode.mode)

fun Context.openOutputStream(uri: Uri, mode: FileMode) =
    contentResolver.openOutputStream(uri, mode.mode)

internal suspend fun Context.clearCache() = coroutineScope {
    runCatching {
        cacheDir?.deleteRecursively()
        codeCacheDir?.deleteRecursively()
        externalCacheDir?.deleteRecursively()
        externalCacheDirs?.forEach {
            it.deleteRecursively()
        }
    }
}

internal fun Context.cacheSize(): Long = runCatching {
    val cache =
        cacheDir?.walkTopDown()?.sumOf { if (it.isFile) it.length() else 0 } ?: 0
    val code =
        codeCacheDir?.walkTopDown()?.sumOf { if (it.isFile) it.length() else 0 } ?: 0
    var size = cache + code
    externalCacheDirs?.forEach { file ->
        size += file?.walkTopDown()?.sumOf { if (it.isFile) it.length() else 0 } ?: 0
    }

    size
}.getOrNull() ?: 0


fun Context.isInstalledFromPlayStore(): Boolean = verifyInstallerId(
    listOf(
        "com.android.vending",
        "com.google.android.feedback"
    )
)

private fun Context.verifyInstallerId(
    validInstallers: List<String>,
): Boolean = validInstallers.contains(getInstallerPackageName(packageName))

private fun Context.getInstallerPackageName(
    packageName: String
): String? = runCatching {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        packageManager.getInstallSourceInfo(packageName).installingPackageName
    } else {
        @Suppress("DEPRECATION")
        packageManager.getInstallerPackageName(packageName)
    }
}.getOrNull()

val Context.density: Density
    get() = object : Density {
        override val density: Float
            get() = resources.displayMetrics.density
        override val fontScale: Float
            get() = resources.configuration.fontScale
    }