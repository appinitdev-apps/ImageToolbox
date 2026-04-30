/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.content_pickers

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.utils.makeLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private data class FilePickerImpl(
    val context: Context,
    val type: FileType,
    val mimeType: MimeType,
    val openDocument: ManagedActivityResultLauncher<Array<String>, Uri?>,
    val openDocumentMultiple: ManagedActivityResultLauncher<Array<String>, List<Uri>>,
    val onFailure: (Throwable) -> Unit
) : FilePicker {

    override fun pickFile() {
        (type to mimeType).makeLog("File Picker Start")

        runCatching {
            when (type) {
                FileType.Single -> openDocument.launch(mimeType.entries.toTypedArray())
                FileType.Multiple -> openDocumentMultiple.launch(mimeType.entries.toTypedArray())
            }
        }.onFailure {
            it.makeLog("File Picker Failure")
            onFailure(it)
        }.onSuccess {
            (type to mimeType).makeLog("File Picker Success")
        }
    }

}

@Stable
@Immutable
interface FilePicker : ResultLauncher {
    fun pickFile()
    override fun launch() = pickFile()
}

enum class FileType {
    Single, Multiple
}

@Composable
fun rememberFilePicker(
    type: FileType,
    mimeType: MimeType = MimeType.All,
    onFailure: () -> Unit = {},
    onSuccess: (List<Uri>) -> Unit,
): FilePicker {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val openDocument = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            scope.launch {
                delay(300)
                uri?.takeIf {
                    it != Uri.EMPTY
                }?.let {
                    onSuccess(listOf(it))
                } ?: onFailure()
            }
        }
    )
    val openDocumentMultiple = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            scope.launch {
                delay(300)
                uris.takeIf { it.isNotEmpty() }?.let(onSuccess) ?: onFailure()
            }
        }
    )

    return remember(
        type,
        mimeType,
        openDocument,
        openDocumentMultiple
    ) {
        derivedStateOf {
            FilePickerImpl(
                context = context,
                type = type,
                mimeType = mimeType,
                openDocument = openDocument,
                openDocumentMultiple = openDocumentMultiple,
                onFailure = {
                    onFailure()
                    AppToastHost.handleFileSystemFailure(it)
                }
            )
        }
    }.value
}

@JvmName("rememberMultipleFilePicker")
@Composable
fun rememberFilePicker(
    mimeType: MimeType = MimeType.All,
    onFailure: () -> Unit = {},
    onSuccess: (List<Uri>) -> Unit,
): FilePicker = rememberFilePicker(
    type = FileType.Multiple,
    mimeType = mimeType,
    onFailure = onFailure,
    onSuccess = onSuccess
)

@JvmName("rememberSingleFilePicker")
@Composable
fun rememberFilePicker(
    mimeType: MimeType = MimeType.All,
    onFailure: () -> Unit = {},
    onSuccess: (Uri) -> Unit,
): FilePicker = rememberFilePicker(
    type = FileType.Single,
    mimeType = mimeType,
    onFailure = onFailure,
    onSuccess = {
        it.firstOrNull()?.let(onSuccess)
    }
)