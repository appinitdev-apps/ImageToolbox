/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.data.project

import android.net.Uri
import com.t8rin.imagetoolbox.core.utils.appContext
import com.t8rin.imagetoolbox.core.utils.filename

fun Uri.isMarkupProject(): Boolean {
    val name = filename(appContext).orEmpty()
    val uri = toString()

    return name.isMarkupProjectFilename() || uri.isMarkupProjectFilename()
}

private fun String.isMarkupProjectFilename(): Boolean {
    val value = lowercase()
    return value.endsWith(".$MarkupProjectExtension") ||
            value.endsWith(".$MarkupProjectExtension.zip")
}