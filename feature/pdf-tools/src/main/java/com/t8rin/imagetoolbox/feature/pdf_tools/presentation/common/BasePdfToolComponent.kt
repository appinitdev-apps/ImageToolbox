/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.model.ExtraDataType
import com.t8rin.imagetoolbox.core.domain.model.MimeType
import com.t8rin.imagetoolbox.core.domain.saving.KeepAliveService
import com.t8rin.imagetoolbox.core.domain.saving.model.SaveResult
import com.t8rin.imagetoolbox.core.domain.utils.runSuspendCatching
import com.t8rin.imagetoolbox.core.domain.utils.smartJob
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import com.t8rin.imagetoolbox.core.utils.makeLog
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.PdfManager
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfCheckResult
import kotlinx.coroutines.Job

abstract class BasePdfToolComponent(
    val onGoBack: () -> Unit,
    val onNavigate: (Screen) -> Unit,
    dispatchersHolder: DispatchersHolder,
    componentContext: ComponentContext,
    private val pdfManager: PdfManager,
) : BaseComponent(dispatchersHolder, componentContext) {

    private val _isSaving: MutableState<Boolean> = mutableStateOf(false)
    val isSaving by _isSaving

    protected val _showPasswordRequestDialog: MutableState<Boolean> = mutableStateOf(false)
    val showPasswordRequestDialog by _showPasswordRequestDialog

    protected var isRtl = false

    open val extraDataType: ExtraDataType? = ExtraDataType.Pdf
    open val mimeType: MimeType.Single = MimeType.Pdf

    init {
        doOnDestroy {
            pdfManager.setMasterPassword(null)
        }
    }

    protected var savingJob: Job? by smartJob {
        _isSaving.update { false }
    }

    open fun setPassword(password: String) {
        _showPasswordRequestDialog.update { false }
        pdfManager.setMasterPassword(password)
    }

    fun hidePasswordRequestDialog() {
        _showPasswordRequestDialog.update { false }
    }

    fun updateIsRtl(isRtl: Boolean) {
        this.isRtl = isRtl
    }

    fun checkPdf(
        uri: Uri?,
        onDecrypted: (Uri) -> Unit,
        onSuccess: (Uri) -> Unit = {}
    ) {
        if (uri == null) return

        componentScope.launch {
            when (val result = pdfManager.checkPdf(uri.toString())) {
                is PdfCheckResult.Open -> onSuccess(uri)

                is PdfCheckResult.Protected.NeedsPassword -> _showPasswordRequestDialog.update { true }

                is PdfCheckResult.Protected.Unlocked -> {
                    pdfManager.setMasterPassword(null)
                    onDecrypted(result.decryptedUri.toUri())
                    onSuccess(result.decryptedUri.toUri())
                }

                is PdfCheckResult.Failure -> result.throwable.makeLog("checkPdf")
            }
        }
    }

    fun cancelSaving() {
        savingJob?.cancel()
        savingJob = null
        _isSaving.value = false
    }

    open fun createTargetFilename(): String = getKey().let {
        pdfManager.createTempName(
            key = it?.first.orEmpty(),
            uri = it?.second?.toString()
        )
    }

    protected open fun getKey(): Pair<String, Uri?>? = null

    abstract fun saveTo(uri: Uri)

    abstract fun performSharing(
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    )

    abstract fun prepareForSharing(
        onSuccess: suspend (List<Uri>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    protected fun doSaving(
        action: suspend KeepAliveService.() -> SaveResult
    ) {
        savingJob = trackProgress {
            runSuspendCatching {
                _isSaving.value = true
                parseFileSaveResult(action())
            }.onFailure {
                if (it is SecurityException) {
                    _showPasswordRequestDialog.update { true }
                } else {
                    parseFileSaveResult(SaveResult.Error.Exception(it))
                }
            }
            _isSaving.value = false
        }
    }

    protected fun <T> doSharing(
        action: suspend KeepAliveService.() -> T,
        onSuccess: (T) -> Unit = {},
        onFailure: (Throwable) -> Unit
    ) {
        savingJob = trackProgress {
            runSuspendCatching {
                _isSaving.value = true
                onSuccess(action())
            }.onFailure {
                if (it is SecurityException) {
                    _showPasswordRequestDialog.update { true }
                } else {
                    onFailure(it)
                }
            }
            _isSaving.value = false
        }
    }

}