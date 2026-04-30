/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.coil

import coil3.memory.MemoryCache

fun MemoryCache.remove(key: String) = keys.filter {
    it.key == key
}.ifEmpty {
    listOf(MemoryCache.Key(key))
}.all(::remove)