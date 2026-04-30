/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.settings.presentation.model

import android.os.Build
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.FolderImage
import com.t8rin.imagetoolbox.core.resources.icons.ImageEmbedded
import com.t8rin.imagetoolbox.core.resources.icons.ImagesMode
import com.t8rin.imagetoolbox.core.resources.icons.PhotoCameraBack
import com.t8rin.imagetoolbox.core.resources.icons.PhotoPickerMobile

sealed class PicturePickerMode(
    val ordinal: Int,
    val icon: ImageVector,
    val title: Int,
    val subtitle: Int
) {
    data object Embedded : PicturePickerMode(
        ordinal = 0,
        icon = Icons.Outlined.ImageEmbedded,
        title = R.string.embedded_picker,
        subtitle = R.string.embedded_picker_sub
    )

    data object PhotoPicker : PicturePickerMode(
        ordinal = 1,
        icon = Icons.Outlined.PhotoPickerMobile,
        title = R.string.photo_picker,
        subtitle = R.string.photo_picker_sub
    )

    data object Gallery : PicturePickerMode(
        ordinal = 2,
        icon = Icons.Outlined.ImagesMode,
        title = R.string.gallery_picker,
        subtitle = R.string.gallery_picker_sub
    )

    data object GetContent : PicturePickerMode(
        ordinal = 3,
        icon = Icons.Outlined.FolderImage,
        title = R.string.file_explorer_picker,
        subtitle = R.string.file_explorer_picker_sub
    )

    data object CameraCapture : PicturePickerMode(
        ordinal = 4,
        icon = Icons.Outlined.PhotoCameraBack,
        title = R.string.camera,
        subtitle = R.string.camera_sub
    )

    companion object {

        val SafeEmbedded by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Embedded
            } else PhotoPicker
        }

        fun fromInt(
            ordinal: Int
        ) = when (ordinal) {
            0 -> SafeEmbedded
            1 -> PhotoPicker
            2 -> Gallery
            3 -> GetContent
            4 -> CameraCapture
            else -> SafeEmbedded
        }

        val entries by lazy {
            listOf(
                SafeEmbedded, PhotoPicker, Gallery, GetContent, CameraCapture
            ).distinct()
        }
    }
}