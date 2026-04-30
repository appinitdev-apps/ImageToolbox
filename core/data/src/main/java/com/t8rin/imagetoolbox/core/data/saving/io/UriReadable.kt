/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.saving.io

import android.content.Context
import android.net.Uri
import com.t8rin.imagetoolbox.core.data.utils.openWriteableStream
import com.t8rin.imagetoolbox.core.utils.makeLog
import io.ktor.utils.io.charsets.Charset
import java.io.ByteArrayOutputStream


class UriReadable(
    private val uri: Uri,
    private val context: Context
) : StreamReadable by StreamReadable(
    inputStream = context.contentResolver.openInputStream(uri) ?: ByteArray(0).inputStream()
)

class UriWriteable(
    private val uri: Uri,
    private val context: Context
) : StreamWriteable by StreamWriteable(
    outputStream = context.openWriteableStream(
        uri = uri,
        onFailure = {
            uri.makeLog("UriWriteable write")
            it.makeLog("UriWriteable write")
            throw it
        }
    ) ?: ByteArrayOutputStream(0)
)

class ByteArrayReadable(
    private val byteArray: ByteArray
) : StreamReadable by StreamReadable(
    inputStream = byteArray.inputStream()
)

class StringReadable(
    private val string: String,
    private val charset: Charset = Charsets.UTF_8
) : StreamReadable by ByteArrayReadable(
    byteArray = string.toByteArray(charset)
)