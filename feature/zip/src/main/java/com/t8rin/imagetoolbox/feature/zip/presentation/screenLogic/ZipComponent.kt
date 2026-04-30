/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.zip.presentation.screenLogic

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ShareProvider
import com.t8rin.imagetoolbox.core.domain.saving.FileController
import com.t8rin.imagetoolbox.core.domain.saving.updateProgress
import com.t8rin.imagetoolbox.core.domain.utils.runSuspendCatching
import com.t8rin.imagetoolbox.core.domain.utils.smartJob
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import com.t8rin.imagetoolbox.feature.zip.domain.ZipManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job

class ZipComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted val initialUris: List<Uri>?,
    @Assisted val onGoBack: () -> Unit,
    private val zipManager: ZipManager,
    private val shareProvider: ShareProvider,
    private val fileController: FileController,
    dispatchersHolder: DispatchersHolder
) : BaseComponent(dispatchersHolder, componentContext) {

    init {
        debounce {
            initialUris?.let(::setUris)
        }
    }

    private val _uris = mutableStateOf<List<Uri>>(emptyList())
    val uris by _uris

    private val _compressedArchiveUri = mutableStateOf<String?>(null)
    val compressedArchiveUri by _compressedArchiveUri

    private val _isSaving: MutableState<Boolean> = mutableStateOf(false)
    val isSaving by _isSaving

    private val _done: MutableState<Int> = mutableIntStateOf(0)
    val done by _done

    private val _left: MutableState<Int> = mutableIntStateOf(-1)
    val left by _left

    fun setUris(newUris: List<Uri>) {
        _uris.update { newUris.distinct() }
        resetCalculatedData()
    }

    private var savingJob: Job? by smartJob {
        _isSaving.update { false }
    }

    fun startCompression() {
        savingJob = trackProgress {
            _isSaving.value = true
            if (uris.isEmpty()) {
                return@trackProgress
            }
            runSuspendCatching {
                _done.update { 0 }
                _left.update { uris.size }
                _compressedArchiveUri.value = zipManager.zip(
                    files = uris.map { it.toString() },
                    onProgress = {
                        _done.update { it + 1 }
                        updateProgress(
                            done = done,
                            total = left
                        )
                    }
                )
            }.onFailure(AppToastHost::showFailureToast)
            _isSaving.value = false
        }
    }

    private fun resetCalculatedData() {
        _compressedArchiveUri.value = null
    }

    fun saveResultTo(uri: Uri) {
        savingJob = trackProgress {
            _isSaving.value = true
            _compressedArchiveUri.value?.let { byteArray ->
                fileController.transferBytes(
                    fromUri = byteArray,
                    toUri = uri.toString(),
                ).also(::parseFileSaveResult).onSuccess(::registerSave)
            }
            _isSaving.value = false
        }
    }

    fun shareFile() {
        compressedArchiveUri?.let { uri ->
            savingJob = trackProgress {
                _done.update { 0 }
                _left.update { 0 }

                _isSaving.value = true
                shareProvider.shareUri(
                    uri = uri,
                    onComplete = {
                        _isSaving.value = false

                    }
                )
            }
        }
    }

    fun cancelSaving() {
        savingJob?.cancel()
        savingJob = null
        _isSaving.value = false
    }

    fun removeUri(uri: Uri) {
        _uris.update { it - uri }
    }

    fun addUris(list: List<Uri>) = setUris(uris + list)


    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            initialUris: List<Uri>?,
            onGoBack: () -> Unit,
        ): ZipComponent
    }
}