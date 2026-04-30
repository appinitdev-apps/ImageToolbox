/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.t8rin.imagetoolbox.core.domain.saving.ObjectSaver
import com.t8rin.imagetoolbox.core.domain.utils.ReadWriteDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <O : Any> ObjectSaver.savable(
    scope: CoroutineScope,
    initial: O,
    key: String = initial::class.simpleName.toString(),
    delay: Long = 0,
): ReadWriteDelegate<O> = ObjectSaverDelegate(
    delay = delay,
    saver = this,
    scope = scope,
    initial = initial,
    key = key
)

private class ObjectSaverDelegate<O : Any>(
    delay: Long,
    private val saver: ObjectSaver,
    private val scope: CoroutineScope,
    initial: O,
    private val key: String
) : ReadWriteDelegate<O> {

    private var value: O by mutableStateOf(initial)

    init {
        scope.launch {
            if (delay > 0) delay(delay)
            value = saver.restoreObject(
                key = key,
                kClass = initial::class
            ) ?: initial
        }
    }

    override fun set(value: O) {
        this.value = value
        scope.launch {
            saver.saveObject(
                key = key,
                value = value
            )
        }
    }

    override fun get(): O = value

}