/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.collages.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import coil3.imageLoader
import coil3.memory.MemoryCache
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.toBitmap
import com.t8rin.collages.public.CollageConstants.requestMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal object ImageDecoder {
    var SAMPLER_SIZE = 1536

    suspend fun decodeFileToBitmap(
        context: Context,
        pathName: Uri
    ): Bitmap? = withContext(Dispatchers.IO) {
        val stringKey = pathName.toString() + SAMPLER_SIZE + "ImageDecoder"
        val key = MemoryCache.Key(stringKey)

        context.imageLoader.memoryCache?.get(key)?.image?.toBitmap() ?: context.imageLoader.execute(
            ImageRequest.Builder(context)
                .allowHardware(false)
                .diskCacheKey(stringKey)
                .memoryCacheKey(key)
                .data(pathName)
                .size(SAMPLER_SIZE)
                .run(requestMapper)
                .build()
        ).image?.toBitmap()?.apply {
            if (config != Bitmap.Config.ARGB_8888) {
                setConfig(Bitmap.Config.ARGB_8888)
            }
        }
    }

}