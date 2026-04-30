/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.utils

import kotlin.reflect.KProperty

interface ReadWriteDelegate<V> : ReadOnlyDelegate<V> {
    fun set(value: V)

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: V) = set(value)
}

interface ReadOnlyDelegate<V> {
    fun get(): V

    operator fun getValue(thisRef: Any, property: KProperty<*>): V = get()
}

inline fun <T> ReadWriteDelegate<T>.update(
    transform: (T) -> T
): T = run {
    transform(get()).also(::set)
}