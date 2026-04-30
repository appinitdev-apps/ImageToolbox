/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.saving

import com.t8rin.imagetoolbox.core.domain.saving.model.ImageSaveTarget

interface FilenameCreator {

    fun constructImageFilename(
        saveTarget: ImageSaveTarget,
        oneTimePrefix: String? = null,
        forceNotAddSizeInFilename: Boolean = false,
        pattern: String? = null
    ): String

    fun constructRandomFilename(
        extension: String,
        length: Int = 32
    ): String

    fun getFilename(uri: String): String

}