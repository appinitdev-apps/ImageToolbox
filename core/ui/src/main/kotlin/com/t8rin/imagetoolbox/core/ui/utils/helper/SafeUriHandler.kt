/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.UriHandler
import androidx.core.net.toUri
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.utils.getString

@Composable
fun rememberSafeUriHandler(): UriHandler {
    val activity = LocalActivity.current

    return remember(activity) {
        SafeUriHandler(
            activity = activity
        )
    }
}

@Stable
@Immutable
private class SafeUriHandler(
    private val activity: Activity?
) : UriHandler {

    override fun openUri(uri: String) {
        tryActions(
            first = { rawOpenUri(uri) },
            second = {
                val trimmed = uri.trim()

                val modifiedUrl = when {
                    trimmed.startsWith(WWW, ignoreCase = true) -> trimmed.replace(WWW, HTTPS)
                    !trimmed.startsWith(HTTP) && !trimmed.startsWith(HTTPS) -> HTTPS + trimmed
                    else -> trimmed
                }

                rawOpenUri(modifiedUrl)
            },
            onFailure = {
                AppToastHost.showFailureToast(getString(R.string.cannot_open_uri, uri))
            }
        )
    }

    private fun rawOpenUri(uri: String) {
        activity?.startActivity(
            Intent(Intent.ACTION_VIEW, uri.toUri()).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    private fun tryActions(
        first: () -> Unit,
        second: () -> Unit,
        onFailure: () -> Unit
    ) {
        runCatching(first).onSuccess { return }
        runCatching(second).onSuccess { return }
        onFailure()
    }

    fun asUnsafe(): UriHandler = object : UriHandler {
        override fun openUri(uri: String) = rawOpenUri(uri)
    }

}

fun UriHandler.asUnsafe(): UriHandler = if (this is SafeUriHandler) asUnsafe() else this

private const val WWW = "www."
private const val HTTPS = "https://"
private const val HTTP = "http://"