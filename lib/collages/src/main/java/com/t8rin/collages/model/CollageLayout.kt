/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.collages.model

import android.net.Uri
import com.t8rin.collages.utils.ParamsManager
import com.t8rin.collages.view.PhotoItem

internal data class CollageLayout(
    val preview: Uri,
    val title: String,
    val paramsManager: ParamsManager? = null,
    val photoItemList: List<PhotoItem> = emptyList()
)