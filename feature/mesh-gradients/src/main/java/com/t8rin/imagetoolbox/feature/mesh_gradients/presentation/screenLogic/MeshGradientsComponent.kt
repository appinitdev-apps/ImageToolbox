/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.mesh_gradients.presentation.screenLogic

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import com.arkivanov.decompose.ComponentContext
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.domain.image.ImageGetter
import com.t8rin.imagetoolbox.core.domain.image.ImageShareProvider
import com.t8rin.imagetoolbox.core.domain.remote.DownloadProgress
import com.t8rin.imagetoolbox.core.domain.remote.RemoteResources
import com.t8rin.imagetoolbox.core.domain.remote.RemoteResourcesStore
import com.t8rin.imagetoolbox.core.ui.utils.BaseComponent
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

class MeshGradientsComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted val onGoBack: () -> Unit,
    @Assisted val onNavigate: (Screen) -> Unit,
    private val shareProvider: ImageShareProvider<Bitmap>,
    private val imageGetter: ImageGetter<Bitmap>,
    dispatchersHolder: DispatchersHolder,
    remoteResourcesStore: RemoteResourcesStore
) : BaseComponent(dispatchersHolder, componentContext) {

    private val _meshGradientUris = mutableStateOf(emptyList<Uri>())
    val meshGradientUris by _meshGradientUris

    private val _meshGradientDownloadProgress: MutableState<DownloadProgress?> =
        mutableStateOf(null)
    val meshGradientDownloadProgress by _meshGradientDownloadProgress

    init {
        componentScope.launch {
            delay(200)
            val resources = remoteResourcesStore
                .getResources(
                    name = RemoteResources.MESH_GRADIENTS,
                    forceUpdate = true,
                    onDownloadRequest = {
                        launch {
                            remoteResourcesStore.downloadResources(
                                name = RemoteResources.MESH_GRADIENTS,
                                onProgress = { _meshGradientDownloadProgress.value = it },
                                onFailure = {},
                                downloadOnlyNewData = true
                            )
                        }
                        null
                    }
                )

            _meshGradientUris.value = resources?.list?.map { it.uri.toUri() } ?: emptyList()
        }
    }

    fun shareImages(
        uriList: List<Uri>
    ) = componentScope.launch {
        shareProvider.shareImages(
            uris = uriList.map { it.toString() },
            imageLoader = {
                imageGetter.getImage(it)?.run { image to imageInfo }
            },
            onProgressChange = {}
        )
        AppToastHost.showConfetti()
    }

    @AssistedFactory
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onGoBack: () -> Unit,
            onNavigate: (Screen) -> Unit
        ): MeshGradientsComponent
    }

}