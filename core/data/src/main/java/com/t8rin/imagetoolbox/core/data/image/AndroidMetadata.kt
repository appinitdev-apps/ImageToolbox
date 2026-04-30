/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image

import com.t8rin.exif.ExifInterface
import com.t8rin.imagetoolbox.core.domain.image.Metadata
import com.t8rin.imagetoolbox.core.domain.image.model.MetadataTag
import com.t8rin.imagetoolbox.core.domain.image.toMap
import java.io.FileDescriptor

private data class ExifInterfaceMetadata(
    private val exifInterface: ExifInterface
) : Metadata {

    override fun saveAttributes(): Metadata = apply {
        exifInterface.saveAttributes()
    }

    override fun getAttribute(
        tag: MetadataTag
    ): String? = exifInterface.getAttribute(tag.key)

    override fun setAttribute(
        tag: MetadataTag,
        value: String?
    ): Metadata = apply {
        exifInterface.setAttribute(tag.key, value)
    }

    override fun toString(): String = "Android(${toMap()})"

}

internal fun ExifInterface.toMetadata(): Metadata = ExifInterfaceMetadata(this)

internal fun FileDescriptor.toMetadata(): Metadata = ExifInterface(this).toMetadata()