/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.json

import com.squareup.moshi.Moshi
import com.squareup.moshi.rawType
import com.t8rin.imagetoolbox.core.domain.json.JsonParser
import com.t8rin.imagetoolbox.core.utils.makeLog
import java.lang.reflect.Type
import javax.inject.Inject

internal class MoshiParser @Inject constructor(
    private val moshi: Moshi
) : JsonParser {

    override fun <T> toJson(
        obj: T,
        type: Type,
    ): String? = runCatching {
        moshi.adapter<T>(type).toJson(obj)
    }.onFailure { it.makeLog("MoshiParser toJson T = ${obj?.run { this::class }}") }.getOrNull()

    override fun <T> fromJson(
        json: String,
        type: Type,
    ): T? = if (json.isBlank()) {
        "json is empty".makeLog("MoshiParser fromJson T = ${type.rawType.name}")
        null
    } else {
        runCatching {
            moshi.adapter<T>(type).fromJson(json)
        }.onFailure { it.makeLog("MoshiParser fromJson T = ${type.rawType.name}") }.getOrNull()
    }

}