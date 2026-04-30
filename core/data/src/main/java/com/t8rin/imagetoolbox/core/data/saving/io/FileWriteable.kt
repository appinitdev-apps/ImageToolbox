/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.saving.io

import java.io.File

class FileWriteable(
    private val file: File
) : StreamWriteable by StreamWriteable(file.outputStream())

class FileReadable(
    private val file: File
) : StreamReadable by StreamReadable(file.inputStream())