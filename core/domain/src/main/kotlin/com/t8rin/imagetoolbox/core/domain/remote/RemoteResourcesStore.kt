/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.remote

interface RemoteResourcesStore {

    /**
     *  Get cached remote resources
     *
     *      val resourcesStore: RemoteResourcesStore = ...
     *
     *      resourcesStore.getResources(
     *          name = RemoteResources.CUBE_LUT,
     *          forceUpdate = true,
     *          onDownloadRequest = { name ->
     *              resourcesStore.downloadResources(
     *                  name = name,
     *                  onProgress = { progress ->
     *
     *                  },
     *                  onFailure = { throwable ->
     *
     *                  }
     *              )
     *          }
     *     )
     **/
    suspend fun getResources(
        name: String,
        forceUpdate: Boolean,
        onDownloadRequest: suspend (name: String) -> RemoteResources?
    ): RemoteResources?


    /**
     * Download Resources from [ImageToolboxRemoteResources](https://github.com/T8RIN/ImageToolboxRemoteResources)
     *
     *      val resourcesStore: RemoteResourcesStore = ...
     *
     *      resourcesStore.downloadResources(
     *           name = name,
     *           onProgress = { progress ->
     *
     *           },
     *           onFailure = { throwable ->
     *
     *           }
     *      )
     */
    suspend fun downloadResources(
        name: String,
        onProgress: (DownloadProgress) -> Unit,
        onFailure: (Throwable) -> Unit,
        downloadOnlyNewData: Boolean = false
    ): RemoteResources?

}