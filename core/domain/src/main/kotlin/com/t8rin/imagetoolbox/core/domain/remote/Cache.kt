/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.remote

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Instant

private data class CacheItem<V>(
    val value: V,
    val created: Instant,
)

class Cache<K, V>(private val maxAge: Duration) {
    private val map: ConcurrentMap<K, CacheItem<V>> = ConcurrentHashMap()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    suspend fun call(key: K, dataGetter: suspend () -> V): V {
        val now = Clock.System.now()
        val prevItem = map[key]?.takeIf {
            it.created + maxAge > now
        }

        return if (prevItem != null) {
            prevItem.value
        } else {
            val data = dataGetter()
            val item = CacheItem(
                value = data,
                created = now
            )
            map[key] = item
            scope.launch {
                delay(maxAge)
                map.remove(key, item)
            }

            data
        }
    }

    fun reset() {
        map.clear()
    }

    fun reset(key: K) {
        map.remove(key)
    }
}