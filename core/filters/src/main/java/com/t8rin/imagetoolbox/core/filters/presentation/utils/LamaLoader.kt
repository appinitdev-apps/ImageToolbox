/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.t8rin.imagetoolbox.core.domain.remote.DownloadProgress
import com.t8rin.neural_tools.inpaint.LaMaProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface LamaLoader {
    val isDownloaded: Boolean
    fun download(): Flow<DownloadProgress>

    companion object Companion : LamaLoader by LamaLoaderImpl
}

private object LamaLoaderImpl : LamaLoader {
    private val _isDownloaded: MutableState<Boolean> =
        mutableStateOf(LaMaProcessor.isDownloaded.value)
    override val isDownloaded: Boolean by _isDownloaded

    init {
        LaMaProcessor.isDownloaded.onEach {
            _isDownloaded.value = it
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun download(): Flow<DownloadProgress> =
        LaMaProcessor.startDownload().map {
            DownloadProgress(
                currentPercent = it.currentPercent,
                currentTotalSize = it.currentTotalSize
            )
        }
}