/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.getSystemService
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.isFromAppFileProvider
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.moveToCache

@Composable
fun rememberClipboardData(): State<List<Uri>> {
    val settingsState = LocalSettingsState.current
    val allowPaste = settingsState.allowAutoClipboardPaste

    val context = LocalContext.current
    val clipboardManager = remember(context) {
        context.getSystemService<ClipboardManager>()
    }

    val clip = remember {
        mutableStateOf(
            if (allowPaste) {
                clipboardManager.clipList()
            } else emptyList()
        )
    }.apply {
        value = if (allowPaste) {
            clipboardManager.clipList()
        } else emptyList()
    }

    val callback = remember {
        ClipboardManager.OnPrimaryClipChangedListener {
            if (allowPaste) {
                clip.value = clipboardManager.clipList()
            }
        }
    }
    DisposableEffect(clipboardManager, allowPaste) {
        if (allowPaste) {
            clipboardManager?.addPrimaryClipChangedListener(callback)
        }
        onDispose {
            clipboardManager?.removePrimaryClipChangedListener(callback)
        }
    }
    return clip
}

@Composable
fun rememberClipboardText(): State<String> {
    val settingsState = LocalSettingsState.current
    val allowPaste = settingsState.allowAutoClipboardPaste

    val context = LocalContext.current
    val clipboardManager = remember(context) {
        context.getSystemService<ClipboardManager>()
    }

    val clip = remember {
        mutableStateOf(
            if (allowPaste) {
                clipboardManager.clipText()
            } else ""
        )
    }.apply {
        value = if (allowPaste) {
            clipboardManager.clipText()
        } else ""
    }

    val callback = remember {
        ClipboardManager.OnPrimaryClipChangedListener {
            if (allowPaste) {
                clip.value = clipboardManager.clipText()
            }
        }
    }
    DisposableEffect(clipboardManager, allowPaste) {
        if (allowPaste) {
            clipboardManager?.addPrimaryClipChangedListener(callback)
        }
        onDispose {
            clipboardManager?.removePrimaryClipChangedListener(callback)
        }
    }
    return clip
}

fun ClipboardManager?.clipList(): List<Uri> = runCatching {
    this?.primaryClip?.clipList()
}.getOrNull() ?: emptyList()

fun ClipboardManager?.clipText(): String = runCatching {
    this?.primaryClip?.getItemAt(0)?.text?.toString()
}.getOrNull() ?: ""

fun ClipData.clipList() = List(
    size = itemCount,
    init = { index ->
        getItemAt(index).uri?.let { uri ->
            if (uri.isFromAppFileProvider()) uri else uri.moveToCache()
        }
    }
).filterNotNull()

fun List<Uri>.toClipData(
    description: String = "Images",
    mimeTypes: Array<String> = arrayOf("image/*")
): ClipData? {
    if (this.isEmpty()) return null

    return ClipData(
        ClipDescription(
            description,
            mimeTypes
        ),
        ClipData.Item(this.first())
    ).apply {
        this@toClipData.drop(1).forEach {
            addItem(ClipData.Item(it))
        }
    }
}

fun CharSequence.toClipData(
    label: String = "plain text"
): ClipData = ClipData.newPlainText(label, this)

fun Uri.asClip(
    context: Context,
    label: String = "Image"
): ClipEntry = ClipEntry(
    ClipData.newUri(
        context.contentResolver,
        label,
        this
    )
)