/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.base64_tools.presentation.screenLogic

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ImageCompressor
import com.t8rin.imagetoolbox.core.domain.image.ImageGetter
import com.t8rin.imagetoolbox.core.domain.image.ImageShareProvider
import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.domain.image.model.ImageInfo
import com.t8rin.imagetoolbox.core.domain.image.model.Quality
import com.t8rin.imagetoolbox.core.domain.saving.FileController
import com.t8rin.imagetoolbox.core.domain.saving.model.ImageSaveTarget
import com.t8rin.imagetoolbox.core.domain.utils.isBase64
import com.t8rin.imagetoolbox.core.domain.utils.smartJob
import com.t8rin.imagetoolbox.core.domain.utils.timestamp
import com.t8rin.imagetoolbox.core.domain.utils.trimToBase64
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import com.t8rin.imagetoolbox.feature.base64_tools.domain.Base64Converter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job

class Base64ToolsComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted initialUri: Uri?,
    @Assisted val onGoBack: () -> Unit,
    @Assisted val onNavigate: (Screen) -> Unit,
    private val imageGetter: ImageGetter<Bitmap>,
    private val shareProvider: ImageShareProvider<Bitmap>,
    private val fileController: FileController,
    private val converter: Base64Converter,
    private val imageCompressor: ImageCompressor<Bitmap>,
    dispatchersHolder: DispatchersHolder,
) : BaseComponent(dispatchersHolder, componentContext) {

    private val _imageFormat = mutableStateOf(ImageFormat.Default)
    val imageFormat by _imageFormat

    private val _quality = mutableStateOf<Quality>(Quality.Base())
    val quality by _quality

    private val _uri = mutableStateOf<Uri?>(null)
    val uri by _uri

    private val _base64String = mutableStateOf("")
    val base64String by _base64String

    private val _isSaving: MutableState<Boolean> = mutableStateOf(false)
    val isSaving by _isSaving

    private var savingJob: Job? by smartJob {
        _isSaving.update { false }
    }

    init {
        debounce {
            initialUri?.let(::setUri)
        }
    }

    fun setUri(uri: Uri) {
        _uri.value = uri

        updateBase64()
    }

    private fun updateBase64() {
        debouncedImageCalculation {
            uri?.let { imageGetter.getImage(it) }?.let { image ->
                shareProvider.cacheImage(
                    image = image,
                    imageInfo = ImageInfo(
                        width = image.width,
                        height = image.height,
                        imageFormat = imageFormat,
                        quality = quality,
                        originalUri = uri.toString()
                    )
                )?.let {
                    _base64String.value = converter.encode(it)
                }
            }
        }
    }

    fun setBase64(base64: String) {
        _base64String.value = base64
        debouncedImageCalculation {
            _uri.value = converter.decode(base64)?.toUri()
        }
    }

    fun setImageFormat(imageFormat: ImageFormat) {
        _imageFormat.update { imageFormat }
        updateBase64()
    }

    fun setQuality(quality: Quality) {
        _quality.update { quality }
        updateBase64()
    }

    fun getFormatForFilenameSelection(): ImageFormat = imageFormat

    fun shareBitmap() {
        savingJob = trackProgress {
            _isSaving.update { true }
            uri?.let { imageGetter.getImage(it) }?.let { image ->
                shareProvider.shareImage(
                    image = image,
                    imageInfo = ImageInfo(
                        width = image.width,
                        height = image.height,
                        imageFormat = imageFormat,
                        quality = quality,
                        originalUri = uri.toString()
                    ),
                    onComplete = AppToastHost::showConfetti
                )
            }
            _isSaving.update { false }
        }
    }

    fun cacheCurrentImage(onComplete: (Uri) -> Unit) {
        savingJob = trackProgress {
            _isSaving.update { true }
            uri?.let { imageGetter.getImage(it) }?.let { image ->
                shareProvider.cacheImage(
                    image = image,
                    imageInfo = ImageInfo(
                        width = image.width,
                        height = image.height,
                        imageFormat = imageFormat,
                        quality = quality,
                        originalUri = uri.toString()
                    )
                )?.let { uri ->
                    onComplete(uri.toUri())
                }
            }
            _isSaving.update { false }
        }
    }

    fun cancelSaving() {
        savingJob?.cancel()
        savingJob = null
        _isSaving.update { false }
    }

    fun saveBitmap(
        oneTimeSaveLocationUri: String?
    ) {
        savingJob = trackProgress {
            _isSaving.update { true }
            uri?.let { imageGetter.getImage(it) }?.let { image ->
                val imageInfo = ImageInfo(
                    width = image.width,
                    height = image.height,
                    imageFormat = imageFormat,
                    quality = quality,
                    originalUri = uri.toString()
                )
                parseSaveResult(
                    fileController.save(
                        saveTarget = ImageSaveTarget(
                            imageInfo = imageInfo,
                            metadata = null,
                            originalUri = uri.toString(),
                            sequenceNumber = null,
                            data = imageCompressor.compressAndTransform(
                                image = image,
                                imageInfo = imageInfo.copy(
                                    originalUri = uri.toString()
                                )
                            )
                        ),
                        keepOriginalMetadata = true,
                        oneTimeSaveLocationUri = oneTimeSaveLocationUri
                    ).onSuccess(::registerSave)
                )
            }
            _isSaving.update { false }
        }
    }

    fun saveContentToTxt(uri: Uri) {
        base64String.takeIf { it.isNotEmpty() }?.let { data ->
            componentScope.launch {
                fileController.writeBytes(
                    uri = uri.toString(),
                    block = {
                        it.writeBytes(data.encodeToByteArray())
                    }
                ).also(::parseFileSaveResult).onSuccess(::registerSave)
            }
        }
    }

    fun generateTextFilename(): String = "Base64_${timestamp()}.txt"

    fun shareText() {
        base64String.takeIf { it.isNotEmpty() }?.let { data ->
            componentScope.launch {
                shareProvider.shareData(
                    writeData = {
                        it.writeBytes(data.encodeToByteArray())
                    },
                    filename = generateTextFilename()
                )
                AppToastHost.showConfetti()
            }
        }
    }

    fun setBase64FromUri(
        uri: Uri,
        onFailure: () -> Unit
    ) {
        componentScope.launch {
            val text = fileController.readBytes(uri.toString()).decodeToString().trimToBase64()
            if (text.isBase64()) {
                setBase64(text)
            } else {
                onFailure()
            }
        }
    }

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            initialUri: Uri?,
            onGoBack: () -> Unit,
            onNavigate: (Screen) -> Unit,
        ): Base64ToolsComponent
    }

}