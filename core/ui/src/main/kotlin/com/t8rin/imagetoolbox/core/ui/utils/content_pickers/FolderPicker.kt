/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.content_pickers

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
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.takePersistablePermission
import com.t8rin.imagetoolbox.core.utils.makeLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private data class FolderPickerImpl(
    val openDocumentTree: ManagedActivityResultLauncher<Uri?, Uri?>,
    val onFailure: (Throwable) -> Unit
) : FolderPicker {

    override fun pickFolder(initialLocation: Uri?) {
        "Folder Open Start".makeLog()
        runCatching {
            openDocumentTree.launch(initialLocation)
        }.onFailure {
            it.makeLog("Folder Open Failure")
            onFailure(it)
        }.onSuccess {
            "Folder Open Success".makeLog()
        }
    }

}


@Stable
@Immutable
interface FolderPicker : ResultLauncher {
    fun pickFolder(initialLocation: Uri? = null)
    override fun launch() = pickFolder()
}

@Composable
fun rememberFolderPicker(
    onFailure: () -> Unit = {},
    onSuccess: (Uri) -> Unit,
): FolderPicker {
    val scope = rememberCoroutineScope()
    val openDocumentTree = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree(),
        onResult = { uri ->
            scope.launch {
                delay(300)
                uri?.takeIf {
                    it != Uri.EMPTY
                }?.let {
                    onSuccess(uri.takePersistablePermission())
                } ?: onFailure()
            }
        }
    )

    return remember(openDocumentTree) {
        derivedStateOf {
            FolderPickerImpl(
                openDocumentTree = openDocumentTree,
                onFailure = {
                    onFailure()
                    AppToastHost.handleFileSystemFailure(it)
                }
            )
        }
    }.value
}