/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.utils

import java.io.InputStream
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

inline fun <T> OutputStream.createZip(
    block: (ZipOutputStream) -> T
): T = ZipOutputStream(this).use(block)

fun ZipOutputStream.putEntry(
    name: String,
    input: InputStream
) {
    putNextEntry(ZipEntry(name))
    input.use { it.copyTo(this) }
    closeEntry()
}

fun ZipOutputStream.putEntry(
    name: String,
    write: (ZipOutputStream) -> Unit
) {
    putNextEntry(ZipEntry(name))
    write(this)
    closeEntry()
}