/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.utils

import com.t8rin.imagetoolbox.core.data.saving.io.StreamWriteable
import com.t8rin.imagetoolbox.core.domain.saving.io.Writeable
import java.io.OutputStream

fun Writeable.outputStream(): OutputStream = if (this is StreamWriteable) {
    stream
} else {
    object : OutputStream() {
        override fun write(b: Int) = writeBytes(byteArrayOf(b.toByte()))
    }
}