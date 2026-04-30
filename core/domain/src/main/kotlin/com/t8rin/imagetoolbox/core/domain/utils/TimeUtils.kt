/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun timestamp(
    format: String? = "yyyy-MM-dd_HH-mm-ss",
    date: Long? = null
): String = runCatching {
    val dateObject = date?.let(::Date) ?: Date()

    format?.let {
        SimpleDateFormat(format, Locale.getDefault()).format(dateObject)
    } ?: dateObject.time.toString()
}.getOrNull() ?: Date().time.toString()