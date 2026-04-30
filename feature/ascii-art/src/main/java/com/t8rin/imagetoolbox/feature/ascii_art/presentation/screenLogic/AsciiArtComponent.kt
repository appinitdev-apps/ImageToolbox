/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ascii_art.presentation.screenLogic

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import coil3.transform.Transformation
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ImageGetter
import com.t8rin.imagetoolbox.core.filters.domain.FilterProvider
import com.t8rin.imagetoolbox.core.filters.domain.model.params.AsciiParams
import com.t8rin.imagetoolbox.core.filters.presentation.model.UiAsciiFilter
import com.t8rin.imagetoolbox.core.filters.presentation.model.UiNegativeFilter
import com.t8rin.imagetoolbox.core.settings.domain.SettingsProvider
import com.t8rin.imagetoolbox.core.settings.presentation.model.asFontType
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.helper.toCoil
import com.t8rin.imagetoolbox.core.ui.utils.state.update
import com.t8rin.imagetoolbox.feature.ascii_art.domain.AsciiConverter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AsciiArtComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted val initialUri: Uri?,
    @Assisted val onGoBack: () -> Unit,
    private val imageGetter: ImageGetter<Bitmap>,
    private val asciiConverter: AsciiConverter<Bitmap>,
    private val filterProvider: FilterProvider<Bitmap>,
    settingsProvider: SettingsProvider,
    dispatchersHolder: DispatchersHolder
) : BaseComponent(dispatchersHolder, componentContext) {

    private val _uri: MutableState<Uri> = mutableStateOf(Uri.EMPTY)
    val uri: Uri by _uri

    private val _asciiParams: MutableState<AsciiParams> =
        mutableStateOf(AsciiParams.Default.copy(isGrayscale = true))
    val asciiParams: AsciiParams by _asciiParams

    val gradient: String get() = asciiParams.gradient
    val fontSize: Float get() = asciiParams.fontSize

    private val _isInvertImage: MutableState<Boolean> = mutableStateOf(false)
    val isInvertImage: Boolean by _isInvertImage

    init {
        debounce {
            initialUri?.let(::setUri)
        }

        settingsProvider.settingsState.onEach { settings ->
            _asciiParams.update { it.copy(font = settings.font.asFontType()) }
        }.launchIn(componentScope)
    }

    fun setUri(uri: Uri) {
        _uri.update { uri }
    }

    fun convertToAsciiString(
        onSuccess: (String) -> Unit
    ) {
        debouncedImageCalculation {
            imageGetter.getImage(uri)?.let { image ->
                onSuccess(
                    asciiConverter.imageToAscii(
                        image = image,
                        fontSize = fontSize,
                        gradient = gradient,
                        isInverted = isInvertImage
                    )
                )
            }
        }
    }

    fun setGradient(gradient: String) {
        _asciiParams.update {
            it.copy(gradient = gradient)
        }
    }

    fun setFontSize(fontSize: Float) {
        _asciiParams.update {
            it.copy(fontSize = fontSize)
        }
    }

    fun toggleIsInvertImage() {
        _isInvertImage.update { !it }
    }

    fun getAsciiTransformations(): List<Transformation> = buildList {
        if (isInvertImage) add(UiNegativeFilter())
        add(UiAsciiFilter(asciiParams))
    }.map {
        filterProvider.filterToTransformation(it).toCoil()
    }

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            initialUri: Uri?,
            onGoBack: () -> Unit,
        ): AsciiArtComponent
    }
}