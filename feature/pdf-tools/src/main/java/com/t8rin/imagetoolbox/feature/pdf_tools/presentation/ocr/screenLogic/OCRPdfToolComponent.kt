/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.ocr.screenLogic

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ShareProvider
import com.t8rin.imagetoolbox.core.domain.model.ExtraDataType
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.domain.saving.FileController
import com.t8rin.imagetoolbox.core.domain.utils.timestamp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import com.t8rin.imagetoolbox.core.utils.filename
import com.t8rin.imagetoolbox.core.utils.getString
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.PdfManager
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class OCRPdfToolComponent @AssistedInject internal constructor(
    @Assisted val initialUri: Uri?,
    @Assisted componentContext: ComponentContext,
    @Assisted onGoBack: () -> Unit,
    @Assisted onNavigate: (Screen) -> Unit,
    private val pdfManager: PdfManager,
    private val fileController: FileController,
    private val shareProvider: ShareProvider,
    dispatchersHolder: DispatchersHolder
) : BasePdfToolComponent(
    onGoBack = onGoBack,
    onNavigate = onNavigate,
    dispatchersHolder = dispatchersHolder,
    componentContext = componentContext,
    pdfManager = pdfManager
) {
    override val _haveChanges: MutableState<Boolean> = mutableStateOf(initialUri != null)
    override val haveChanges: Boolean by _haveChanges

    override val extraDataType: ExtraDataType = ExtraDataType.File
    override val mimeType: MimeType.Single = MimeType.Txt

    private val _uri: MutableState<Uri?> = mutableStateOf(initialUri)
    val uri by _uri

    fun setUri(uri: Uri?) {
        if (uri == null) {
            registerChangesCleared()
        } else {
            registerChanges()
        }
        _uri.update { uri }
        checkPdf(
            uri = uri,
            onDecrypted = { _uri.value = it }
        )
    }

    override fun createTargetFilename(): String =
        "${uri?.filename()?.substringBeforeLast('.') ?: timestamp()}_extracted.txt"

    fun navigateToOcr() {
        doSharing(
            action = {
                pdfManager.extractPagesFromPdf(uri.toString())
            },
            onSuccess = { uris ->
                onNavigate(
                    Screen.RecognizeText(Screen.RecognizeText.Type.WriteToFile(uris.map { it.toUri() }))
                )
            },
            onFailure = {
                AppToastHost.showFailureToast(it)
                _uri.value = null
                registerChangesCleared()
            }
        )
    }

    override fun saveTo(
        uri: Uri
    ) {
        doSaving {
            val processed = stripText()

            fileController.writeBytes(
                uri = uri.toString(),
                block = {
                    it.writeBytes(processed)
                }
            ).onSuccess(::registerSave)
        }
    }

    override fun performSharing(
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        prepareForSharing(
            onSuccess = {
                shareProvider.shareUris(it.map(Uri::toString))
                registerSave()
                onSuccess()
            },
            onFailure = onFailure
        )
    }

    override fun prepareForSharing(
        onSuccess: suspend (List<Uri>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        doSharing(
            action = {
                val processed = stripText()

                shareProvider.cacheData(
                    filename = createTargetFilename(),
                    writeData = {
                        it.writeBytes(processed)
                    }
                )?.toUri()?.let {
                    onSuccess(listOf(it))
                    registerSave()
                }
            },
            onFailure = onFailure
        )
    }

    private suspend fun stripText(): ByteArray = pdfManager.stripText(
        uri = _uri.value.toString()
    ).mapIndexed { index, text ->
        "--- ${getString(R.string.page)} ${index + 1} ---\n$text\n\n"
    }.joinToString("").encodeToByteArray()

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            initialUri: Uri?,
            componentContext: ComponentContext,
            onGoBack: () -> Unit,
            onNavigate: (Screen) -> Unit,
        ): OCRPdfToolComponent
    }
}