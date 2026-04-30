/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.saving.io

import com.t8rin.imagetoolbox.core.domain.saving.io.Readable
import com.t8rin.imagetoolbox.core.domain.saving.io.Writeable
import com.t8rin.imagetoolbox.core.utils.makeLog
import java.io.InputStream
import java.io.OutputStream

private class StreamWriteableImpl(
    outputStream: OutputStream
) : StreamWriteable {

    override val stream = outputStream

    override fun writeBytes(byteArray: ByteArray) = stream.write(byteArray)

    override fun close() {
        stream.flush()
        stream.close()
    }

}

private class StreamReadableImpl(
    inputStream: InputStream
) : StreamReadable {

    override val stream = inputStream

    override fun readBytes(): ByteArray = stream.readBytes()

    override fun copyTo(writeable: Writeable) {
        if (writeable is StreamWriteable) {
            stream.copyTo(writeable.stream)
        } else {
            writeable.writeBytes(readBytes())
        }
    }

    override fun close() = stream.close()

}

interface StreamReadable : Readable {
    val stream: InputStream

    companion object {
        operator fun invoke(
            inputStream: InputStream
        ): StreamReadable = StreamReadableImpl(inputStream)
    }
}

interface StreamWriteable : Writeable {
    val stream: OutputStream

    companion object {
        operator fun invoke(
            outputStream: OutputStream
        ): StreamWriteable = StreamWriteableImpl(outputStream)
    }
}

fun StreamWriteable.shielded(): StreamWriteable = CloseShieldWriteable(this)

private class CloseShieldWriteable(wrapped: StreamWriteable) : StreamWriteable by wrapped {
    override fun close() {
        "can't be closed".makeLog("CloseShieldWriteable")
    }
}