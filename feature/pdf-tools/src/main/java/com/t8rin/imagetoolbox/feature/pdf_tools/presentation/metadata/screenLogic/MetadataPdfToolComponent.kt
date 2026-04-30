/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.metadata.screenLogic

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ImageShareProvider
import com.t8rin.imagetoolbox.core.domain.saving.FileController
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.PdfManager
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfMetadata
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.BasePdfToolComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MetadataPdfToolComponent @AssistedInject internal constructor(
    @Assisted val initialUri: Uri?,
    @Assisted componentContext: ComponentContext,
    @Assisted onGoBack: () -> Unit,
    @Assisted onNavigate: (Screen) -> Unit,
    private val pdfManager: PdfManager,
    private val shareProvider: ImageShareProvider<Bitmap>,
    private val fileController: FileController,
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

    private val _uri: MutableState<Uri?> = mutableStateOf(initialUri)
    val uri by _uri

    private val _metadata: MutableState<PdfMetadata> = mutableStateOf(PdfMetadata())
    val metadata by _metadata

    private val _deepClean: MutableState<Boolean> = mutableStateOf(false)
    val deepClean by _deepClean

    override fun getKey(): Pair<String, Uri?> = "metadata" to uri

    fun setUri(uri: Uri?) {
        if (uri == null) {
            registerChangesCleared()
        } else {
            registerChanges()
        }
        _uri.update { uri }
        checkPdf(
            uri = uri,
            onDecrypted = { _uri.value = it },
            onSuccess = { newUri ->
                doSharing(
                    action = {
                        pdfManager.getPdfMetadata(newUri.toString())
                    },
                    onSuccess = { metadata ->
                        _metadata.update { metadata }
                    },
                    onFailure = {
                        _metadata.update { PdfMetadata() }
                    }
                )
                _deepClean.update { false }
            }
        )
    }

    fun updateMetadata(metadata: PdfMetadata) {
        registerChanges()
        _metadata.update { metadata }
    }

    fun updateDeepClean(value: Boolean) {
        registerChanges()
        _deepClean.update { value }
    }

    fun resetMetadata() {
        setUri(uri)
    }

    override fun saveTo(
        uri: Uri
    ) {
        doSaving {
            val processed = pdfManager.changePdfMetadata(
                uri = _uri.value.toString(),
                metadata = metadata.takeIf { !deepClean }?.copy(
                    producer = metadata.producer.orEmpty().ifEmpty { "AppInitDev" }
                )
            )

            fileController.transferBytes(
                fromUri = processed,
                toUri = uri.toString()
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
                onSuccess(
                    listOf(
                        pdfManager.changePdfMetadata(
                            uri = _uri.value.toString(),
                            metadata = metadata.takeIf { !deepClean }?.copy(
                                producer = metadata.producer.orEmpty().ifEmpty { "AppInitDev" }
                            )
                        ).toUri()
                    )
                )
                registerSave()
            },
            onFailure = onFailure
        )
    }

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            initialUri: Uri?,
            componentContext: ComponentContext,
            onGoBack: () -> Unit,
            onNavigate: (Screen) -> Unit,
        ): MetadataPdfToolComponent
    }
}