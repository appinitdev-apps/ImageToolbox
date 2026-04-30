/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.utils

import java.io.InputStream

fun InputStream.withProgress(
    total: Long,
    onProgress: (Float) -> Unit
): InputStream = ProgressInputStream(
    source = this,
    total = total,
    onProgress = onProgress
)

private class ProgressInputStream(
    private val source: InputStream,
    private val total: Long,
    private val onProgress: (Float) -> Unit
) : InputStream() {

    private var readBytes = 0L

    override fun read(): Int {
        val r = source.read()
        if (r != -1) {
            readBytes++
            report()
        }
        return r
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        val r = source.read(b, off, len)
        if (r > 0) {
            readBytes += r
            report()
        }
        return r
    }

    private fun report() {
        if (total > 0) {
            onProgress(readBytes.toFloat() / total)
        }
    }

    override fun close() = source.close()
}