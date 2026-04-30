/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.libraries_info.presentation.components

import com.mikepenz.aboutlibraries.entity.Library

fun Library.link(): String? =
    (scm?.url ?: website ?: licenses.firstOrNull()?.url)?.replace("git://", "")