/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.saving.KeepAliveService
import com.t8rin.imagetoolbox.core.domain.saving.track
import com.t8rin.imagetoolbox.core.domain.utils.smartJob
import com.t8rin.imagetoolbox.core.ui.utils.helper.SaveResultHandler
import com.t8rin.imagetoolbox.core.ui.utils.helper.SaveResultHandlerImpl
import com.t8rin.imagetoolbox.core.ui.utils.navigation.coroutineScope
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import com.t8rin.imagetoolbox.core.utils.makeLog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.launch as internalLaunch

@Stable
@Immutable
abstract class BaseComponent(
    private val dispatchersHolder: DispatchersHolder,
    private val componentContext: ComponentContext
) : ComponentContext by componentContext,
    DispatchersHolder by dispatchersHolder,
    SaveResultHandler by SaveResultHandlerImpl {

    val componentScope = coroutineScope

    protected open val _isImageLoading: MutableState<Boolean> = mutableStateOf(false)
    open val isImageLoading: Boolean by _isImageLoading

    private var imageCalculationJob: Job? by smartJob {
        _isImageLoading.update { false }
    }

    inline fun debounce(
        time: Long = 150,
        crossinline block: suspend () -> Unit
    ) {
        componentScope.launch {
            delay(time)
            block()
        }
    }

    protected open val _haveChanges: MutableState<Boolean> = mutableStateOf(false)
    open val haveChanges: Boolean by _haveChanges

    fun trackProgress(
        action: suspend KeepAliveService.() -> Unit
    ): Job = componentScope.launch {
        keepAliveService.track(
            onFailure = { it.makeLog("CRITICAL") },
            action = action
        )
    }

    protected fun registerSave() {
        _haveChanges.update { false }
    }

    protected fun registerChangesCleared() {
        _haveChanges.update { false }
    }

    protected fun registerChanges() {
        _haveChanges.update { true }
    }

    private var resetJob by smartJob()

    protected open fun debouncedImageCalculation(
        onFinish: suspend () -> Unit = {},
        delay: Long = 600L,
        action: suspend () -> Unit
    ) {
        imageCalculationJob = componentScope.launch(
            CoroutineExceptionHandler { _, t ->
                t.makeLog()
                _isImageLoading.update { false }
            } + defaultDispatcher
        ) {
            _isImageLoading.update { true }
            delay(delay)

            ensureActive()

            action()

            ensureActive()

            _isImageLoading.update { false }

            onFinish()
        }
    }

    private fun cancelResetState() {
        resetJob?.cancel()
        resetJob = null
    }

    private fun resetStateDelayed() {
        resetJob = componentScope.launch {
            delay(1500)
            resetState()
        }
    }

    @Composable
    fun AttachLifecycle() {
        DisposableEffect(Unit) {
            cancelResetState()
            onDispose {
                resetStateDelayed()
            }
        }
    }

    fun cancelImageLoading() {
        _isImageLoading.update { false }
        imageCalculationJob?.cancel()
        imageCalculationJob = null
    }

    open fun resetState(): Unit =
        throw IllegalAccessException("Cannot reset state of ${this::class.simpleName}")

    fun CoroutineScope.launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = internalLaunch(context, start) {
        delay(50L)
        block()
    }

    companion object {
        internal var keepAliveService: KeepAliveService = object : KeepAliveService {
            override fun updateOrStart(
                title: String,
                description: String,
                progress: Float
            ) = Unit

            override fun stop(removeNotification: Boolean) = Unit

            override fun send(error: Throwable) = Unit
        }

        fun inject(keepAliveService: KeepAliveService) {
            this.keepAliveService = keepAliveService
        }
    }
}