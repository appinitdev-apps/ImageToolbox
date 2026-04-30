/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.saving

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.supervisorScope

interface KeepAliveService : FailureNotifier {
    fun updateOrStart(
        title: String = "",
        description: String = "",
        progress: Float = PROGRESS_INDETERMINATE
    )

    fun stop(removeNotification: Boolean = true)

    companion object {
        const val PROGRESS_NO_PROGRESS = -2f
        const val PROGRESS_INDETERMINATE = -1f
    }
}

fun KeepAliveService.updateProgress(
    title: String = "",
    done: Int,
    total: Int
) {
    updateOrStart(
        title = title,
        description = "$done / $total",
        progress = (done / total.toFloat()).takeIf { it > 0f }
            ?: KeepAliveService.PROGRESS_INDETERMINATE
    )
}

suspend fun <R> KeepAliveService.track(
    initial: KeepAliveService.() -> Unit = { updateOrStart() },
    onCancel: () -> Unit = {},
    onFailure: suspend (Throwable) -> Unit = {},
    onComplete: KeepAliveService.(isSuccess: Boolean) -> Unit = { stop(true) },
    action: suspend KeepAliveService.() -> R
): R? = supervisorScope {
    try {
        initial()
        action()
    } catch (e: CancellationException) {
        onComplete(false)
        onCancel()
        throw e
    } catch (e: Throwable) {
        onComplete(false)
        onFailure(e)
        send(e)
        null
    } finally {
        onComplete(true)
    }
}