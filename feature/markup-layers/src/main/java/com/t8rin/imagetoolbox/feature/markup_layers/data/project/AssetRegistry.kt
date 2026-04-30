/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.data.project

import androidx.core.net.toUri
import java.io.File

internal class AssetRegistry {
    private val entryBySource = linkedMapOf<String, AssetSource>()

    fun register(
        source: String,
        proposedEntryName: String
    ): String {
        val key = sourceKey(source)
        return entryBySource[key]?.entryName ?: proposedEntryName.also {
            entryBySource[key] = AssetSource(
                entryName = proposedEntryName,
                source = source
            )
        }
    }

    private fun sourceKey(
        source: String
    ): String = when {
        source.startsWith("android.resource://") -> source
        source.startsWith("content://") -> source
        source.startsWith("file://") -> {
            source.toUri().path
                ?.let(::File)
                ?.canonicalPath
                ?.let { "file:$it" }
                ?: source
        }

        else -> runCatching { File(source).canonicalPath }
            .getOrNull()
            ?.let { "path:$it" }
            ?: source
    }

    fun entries(): List<AssetSource> = entryBySource.values.toList()
}

internal data class AssetSource(
    val entryName: String,
    val source: String
)

internal data class ProjectArchive(
    val projectJson: String,
    val assets: List<AssetSource>
)