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
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.utils.makeLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private data class FileMakerImpl(
    val createDocument: ManagedActivityResultLauncher<String, Uri?>,
    val onFailure: (Throwable) -> Unit
) : FileMaker {

    override fun make(name: String) {
        "File Make Start".makeLog()
        runCatching {
            createDocument.launch(name)
        }.onFailure {
            it.makeLog("File Make Failure")
            onFailure(it)
        }.onSuccess {
            "File Make Success".makeLog()
        }
    }

}


@Stable
@Immutable
interface FileMaker : ResultLauncher {
    fun make(name: String)
    override fun launch() = make("")
}

@Composable
fun rememberFileCreator(
    mimeType: MimeType.Single = MimeType.All,
    onFailure: () -> Unit = {},
    onSuccess: (Uri) -> Unit,
): FileMaker {
    val scope = rememberCoroutineScope()
    val createDocument = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument(mimeType.entry),
        onResult = { uri ->
            scope.launch {
                delay(300)
                uri?.takeIf {
                    it != Uri.EMPTY
                }?.let {
                    onSuccess(it)
                } ?: onFailure()
            }
        }
    )

    return remember(createDocument) {
        derivedStateOf {
            FileMakerImpl(
                createDocument = createDocument,
                onFailure = {
                    onFailure()
                    AppToastHost.handleFileSystemFailure(it)
                }
            )
        }
    }.value
}