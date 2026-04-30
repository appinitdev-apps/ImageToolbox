/* #AppInitDev -> Photo Utility Hub */



@file:Suppress("NOTHING_TO_INLINE")

package com.t8rin.imagetoolbox.core.domain.image

import com.t8rin.imagetoolbox.core.domain.image.model.MetadataTag

interface Metadata {
    fun saveAttributes(): Metadata

    fun getAttribute(tag: MetadataTag): String?

    fun setAttribute(
        tag: MetadataTag,
        value: String?
    ): Metadata
}

private class TagMapMetadata(
    initialTags: Map<MetadataTag, String>
) : Metadata {
    private val tags: MutableMap<MetadataTag, String> = initialTags.toMutableMap()

    override fun saveAttributes(): Metadata = this

    override fun getAttribute(tag: MetadataTag): String? = tags[tag]

    override fun setAttribute(
        tag: MetadataTag,
        value: String?
    ): Metadata = apply {
        if (value == null) {
            tags.remove(tag)
        } else {
            tags[tag] = value
        }
    }

    override fun toString(): String = "ReadOnly(${toMap()})"
}

fun Metadata.readOnly(): Metadata = TagMapMetadata(toMap())

inline operator fun Metadata.get(
    tag: MetadataTag
): String? = getAttribute(tag)

inline operator fun Metadata.set(
    tag: MetadataTag,
    value: String?
): Metadata = setAttribute(tag, value)

inline fun Metadata.clearAttribute(
    tag: MetadataTag
) = apply {
    setAttribute(
        tag = tag,
        value = null
    )
}

inline fun Metadata.clearAttributes(
    attributes: List<MetadataTag>
): Metadata = apply {
    attributes.forEach(::clearAttribute)
}

inline fun Metadata.clearAllAttributes(): Metadata =
    clearAttributes(attributes = MetadataTag.entries)

inline fun Metadata.toMap(): Map<MetadataTag, String> = mutableMapOf<MetadataTag, String>().apply {
    MetadataTag.entries.forEach { tag ->
        getAttribute(tag)?.let { put(tag, it) }
    }
}

inline fun Metadata.copyTo(
    metadata: Metadata,
    tags: List<MetadataTag> = MetadataTag.entries
): Metadata {
    tags.forEach { attr ->
        getAttribute(attr).let { metadata.setAttribute(attr, it) }
    }
    metadata.saveAttributes()

    return metadata
}

fun interface MetadataProvider {
    suspend fun readMetadata(imageUri: String): Metadata?
}