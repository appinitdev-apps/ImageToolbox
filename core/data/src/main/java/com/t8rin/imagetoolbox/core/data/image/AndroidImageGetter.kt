/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image

import android.content.Context
import android.graphics.Bitmap
import androidx.core.net.toUri
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.size.Precision
import coil3.size.Size
import coil3.toBitmap
import com.t8rin.imagetoolbox.core.data.image.utils.static
import com.t8rin.imagetoolbox.core.data.utils.toCoil
import com.t8rin.imagetoolbox.core.domain.coroutines.AppScope
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ImageGetter
import com.t8rin.imagetoolbox.core.domain.image.MetadataProvider
import com.t8rin.imagetoolbox.core.domain.image.model.ImageData
import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.domain.image.model.ImageInfo
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.saving.FailureNotifier
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.domain.utils.runSuspendCatching
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.settings.domain.SettingsProvider
import com.t8rin.imagetoolbox.core.utils.extension
import com.t8rin.imagetoolbox.core.utils.makeLog
import dagger.Lazy
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AndroidImageGetter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val imageLoader: ImageLoader,
    private val appScope: AppScope,
    private val failureNotifier: FailureNotifier,
    metadataProvider: Lazy<MetadataProvider>,
    settingsProvider: SettingsProvider,
    dispatchersHolder: DispatchersHolder,
) : DispatchersHolder by dispatchersHolder, ImageGetter<Bitmap> {

    private val _settingsState = settingsProvider.settingsState

    private val settingsState get() = _settingsState.value

    private val metadataProvider by lazy {
        metadataProvider.get()
    }

    override suspend fun getImage(
        uri: String,
        originalSize: Boolean,
        onFailure: ((Throwable) -> Unit)?
    ): ImageData<Bitmap>? = withContext(defaultDispatcher) {
        getImageImpl(
            data = uri,
            size = null,
            addSizeToRequest = originalSize,
            onFailure = onFailure ?: failureNotifier::send
        )?.let { bitmap ->
            ImageData(
                image = bitmap,
                imageInfo = ImageInfo(
                    width = bitmap.width,
                    height = bitmap.height,
                    imageFormat = settingsState.defaultImageFormat
                        ?: ImageFormat[getExtension(uri)],
                    originalUri = uri,
                    resizeType = settingsState.defaultResizeType
                ),
                metadata = metadataProvider.readMetadata(uri)
            )
        }
    }

    override suspend fun getImage(
        data: Any,
        originalSize: Boolean
    ): Bitmap? = getImageImpl(
        data = data,
        size = null,
        addSizeToRequest = originalSize
    )

    override suspend fun getImage(
        data: Any,
        size: IntegerSize?
    ): Bitmap? = getImageImpl(
        data = data,
        size = size
    )

    override suspend fun getImage(
        data: Any,
        size: Int?
    ): Bitmap? = getImageImpl(
        data = data,
        size = size?.let {
            IntegerSize(
                width = it,
                height = it
            )
        },
        precision = Precision.INEXACT
    )

    override suspend fun getImageData(
        uri: String,
        size: Int?,
        onFailure: (Throwable) -> Unit
    ): ImageData<Bitmap>? = withContext(defaultDispatcher) {
        getImageImpl(
            data = uri,
            size = size?.let {
                IntegerSize(
                    width = it,
                    height = it
                )
            },
            precision = Precision.INEXACT,
            onFailure = onFailure
        )?.let { bitmap ->
            ImageData(
                image = bitmap,
                imageInfo = ImageInfo(
                    width = bitmap.width,
                    height = bitmap.height,
                    imageFormat = settingsState.defaultImageFormat
                        ?: ImageFormat[getExtension(uri)],
                    originalUri = uri,
                    resizeType = settingsState.defaultResizeType
                ),
                metadata = metadataProvider.readMetadata(uri)
            )
        }
    }

    override suspend fun getImageWithTransformations(
        uri: String,
        transformations: List<Transformation<Bitmap>>,
        originalSize: Boolean
    ): ImageData<Bitmap>? = withContext(defaultDispatcher) {
        getImageImpl(
            data = uri,
            transformations = transformations,
            size = null,
            addSizeToRequest = originalSize
        )?.let { bitmap ->
            ImageData(
                image = bitmap,
                imageInfo = ImageInfo(
                    width = bitmap.width,
                    height = bitmap.height,
                    imageFormat = ImageFormat[getExtension(uri)],
                    originalUri = uri,
                    resizeType = settingsState.defaultResizeType
                ),
                metadata = metadataProvider.readMetadata(uri)
            )
        }
    }

    override suspend fun getImageWithTransformations(
        data: Any,
        transformations: List<Transformation<Bitmap>>,
        size: IntegerSize?
    ): Bitmap? = getImageImpl(
        data = data,
        transformations = transformations,
        size = size
    )

    override fun getImageAsync(
        uri: String,
        originalSize: Boolean,
        onGetImage: (ImageData<Bitmap>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        appScope.launch {
            var failureDelivered = false

            val imageData = getImage(
                uri = uri,
                originalSize = originalSize,
                onFailure = {
                    failureDelivered = true
                    onFailure(it)
                }
            )

            if (imageData != null) {
                onGetImage(imageData)
            } else if (!failureDelivered) {
                onFailure(
                    IllegalStateException(context.getString(R.string.failed_to_open))
                )
            }
        }
    }

    override fun getExtension(uri: String): String? = uri.toUri().extension(context)

    private suspend fun getImageImpl(
        data: Any,
        size: IntegerSize?,
        precision: Precision = Precision.EXACT,
        transformations: List<Transformation<Bitmap>> = emptyList(),
        onFailure: (Throwable) -> Unit = {},
        addSizeToRequest: Boolean = true
    ): Bitmap? = withContext(defaultDispatcher) {
        if ((size == null || !addSizeToRequest) && data is Bitmap) return@withContext data

        val request = ImageRequest
            .Builder(context)
            .data(data)
            .static()
            .precision(precision)
            .transformations(
                transformations.map(Transformation<Bitmap>::toCoil)
            )
            .apply {
                if (addSizeToRequest) {
                    size(
                        size?.let {
                            Size(size.width, size.height)
                        } ?: Size.ORIGINAL
                    )
                }
            }
            .build()

        runSuspendCatching {
            imageLoader.execute(request).image?.toBitmap()
        }.onFailure {
            it.makeLog("ImageGetter")
            onFailure(it)
        }.getOrNull()
    }

}