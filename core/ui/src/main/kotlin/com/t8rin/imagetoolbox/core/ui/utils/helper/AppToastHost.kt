/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import android.content.ActivityNotFoundException
import androidx.compose.ui.graphics.vector.ImageVector
import com.t8rin.imagetoolbox.core.resources.Icons
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.FolderOff
import com.t8rin.imagetoolbox.core.ui.utils.confetti.ConfettiHostState
import com.t8rin.imagetoolbox.core.ui.widget.other.ToastDuration
import com.t8rin.imagetoolbox.core.ui.widget.other.ToastHostState
import com.t8rin.imagetoolbox.core.ui.widget.other.showFailureToast
import com.t8rin.imagetoolbox.core.utils.appContext
import com.t8rin.imagetoolbox.core.utils.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

data object AppToastHost {

    private var context: CoroutineContext = Dispatchers.Unconfined
    private val scope by lazy { CoroutineScope(context) }

    val state = ToastHostState()

    val confettiState = ConfettiHostState()

    fun init(context: CoroutineContext) {
        this.context = context
    }

    fun showToast(
        message: String,
        icon: ImageVector? = null,
        duration: ToastDuration = ToastDuration.Short
    ) {
        scope.launch {
            state.showToast(
                message = message,
                icon = icon,
                duration = duration
            )
        }
    }

    fun showToast(
        message: Int,
        icon: ImageVector? = null,
        duration: ToastDuration = ToastDuration.Short
    ) {
        scope.launch {
            state.showToast(
                message = getString(message),
                icon = icon,
                duration = duration
            )
        }
    }

    fun showFailureToast(throwable: Throwable) {
        scope.launch {
            state.showFailureToast(
                throwable = throwable
            )
        }
    }

    fun showFailureToast(message: String) {
        scope.launch {
            state.showFailureToast(
                message = message
            )
        }
    }

    fun showFailureToast(res: Int) {
        scope.launch {
            state.showFailureToast(
                message = appContext.getString(res)
            )
        }
    }

    fun dismissToasts() {
        state.currentToastData?.dismiss()
        confettiState.currentToastData?.dismiss()
    }

    fun showConfetti(
        duration: ToastDuration
    ) {
        scope.launch {
            confettiState.showConfetti(duration)
        }
    }

    fun showConfetti() {
        showConfetti(ToastDuration(4500L))
    }

    fun handleFileSystemFailure(throwable: Throwable) {
        when (throwable) {
            is ActivityNotFoundException -> showActivateFilesToast()
            else -> showFailureToast(throwable)
        }
    }

    const val PERMISSION = "REQUEST_PERMISSION"

    private fun showActivateFilesToast() {
        showToast(
            message = appContext.getString(R.string.activate_files),
            icon = Icons.Outlined.FolderOff,
            duration = ToastDuration.Long
        )
    }

}