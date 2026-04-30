/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.media_picker.domain.model

data class MediaState(
    val media: List<Media> = emptyList(),
    val mappedMedia: List<MediaItem> = emptyList(),
    val dateHeader: String = "",
    val error: String = "",
    val isLoading: Boolean = true
)

data class AlbumState(
    val albums: List<Album> = emptyList(),
    val error: String = ""
)