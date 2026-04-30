/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.saving.io

interface Writeable : IoCloseable {

    fun writeBytes(byteArray: ByteArray)

}