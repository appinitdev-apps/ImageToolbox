/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.utils

import java.text.CharacterIterator
import java.text.StringCharacterIterator
import java.util.Locale


fun humanFileSize(
    bytes: Long
): String {
    var tempBytes = bytes
    if (-1024 < tempBytes && tempBytes < 1024) {
        return "$tempBytes B"
    }
    val ci: CharacterIterator = StringCharacterIterator("kMGTPE")
    while (tempBytes <= -999950 || tempBytes >= 999950) {
        tempBytes /= 1024
        ci.next()
    }
    return java.lang.String.format(
        Locale.getDefault(),
        "%.1f %cB",
        tempBytes / 1024.0,
        ci.current()
    ).replace(",0", "")
}