/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.data.utils.serialization

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.TemplateFilter
import kotlin.reflect.full.primaryConstructor

internal fun List<Filter<*>>.toDatastoreString(
    includeValue: Boolean = false
): String = joinToString(separator = FILTERS_SEPARATOR) { filter ->
    filter::class.qualifiedName!!.replace(
        REAL_PACKAGE,
        PACKAGE_ALIAS
    ) + if (includeValue) {
        VALUE_SEPARATOR + filter.value.toPair()
            ?.let { it.first + VALUE_SEPARATOR + it.second }
    } else ""
}.trim()


@Suppress("UNCHECKED_CAST")
internal fun String.toFiltersList(
    includeValue: Boolean
): List<Filter<*>> = split(FILTERS_SEPARATOR).mapNotNull { line ->
    if (line.trim().isEmpty()) return@mapNotNull null

    val (name, value) = if (includeValue) {
        runCatching {
            val splitData = line.split(VALUE_SEPARATOR)
            val className = splitData[1]
            val valueString = splitData[2]

            splitData[0].trim() to (className to valueString).fromPair()
        }.getOrElse { line.trim() to Unit }
    } else line.trim() to Unit
    runCatching {
        val filterClass = Class.forName(
            name.replace(
                PACKAGE_ALIAS,
                REAL_PACKAGE
            )
        ) as Class<Filter<*>>
        filterClass.kotlin.primaryConstructor?.run {
            try {
                if (includeValue && value != null) {
                    callBy(mapOf(parameters[0] to value))
                } else callBy(emptyMap())
            } catch (_: Throwable) {
                callBy(emptyMap())
            }
        }
    }.getOrNull()
}

internal fun String.toTemplateFiltersList(): List<TemplateFilter> =
    split(TEMPLATES_SEPARATOR).map {
        val splitData = it.split(TEMPLATE_CONTENT_SEPARATOR)
        val name = splitData[0]
        val filters = splitData[1].toFiltersList(true)

        TemplateFilter(
            name = name,
            filters = filters
        )
    }

internal fun List<TemplateFilter>.toDatastoreString(): String =
    joinToString(separator = TEMPLATES_SEPARATOR) {
        it.name + TEMPLATE_CONTENT_SEPARATOR + it.filters.toDatastoreString(true)
    }

private const val FILTERS_SEPARATOR = ","
private const val TEMPLATES_SEPARATOR = "\\"
private const val TEMPLATE_CONTENT_SEPARATOR = "+"
private const val VALUE_SEPARATOR = ":"

internal const val REAL_PACKAGE = "com.t8rin.imagetoolbox"
internal const val PACKAGE_ALIAS = "^^"