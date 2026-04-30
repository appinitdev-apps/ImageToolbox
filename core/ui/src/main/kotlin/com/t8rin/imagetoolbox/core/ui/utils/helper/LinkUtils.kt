/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import com.t8rin.imagetoolbox.core.domain.USER_AGENT
import com.t8rin.imagetoolbox.core.domain.remote.Cache
import com.t8rin.imagetoolbox.core.domain.utils.runSuspendCatching
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.jsoup.Jsoup
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

object LinkUtils {
    fun parseLinks(text: String): Set<String> {
        val regex = Regex("""\b(?:https?://|www\.|http?://)\S+\b""")
        val matches = regex.findAll(text)
        return matches.map { it.value }.toSet()
    }
}

@ConsistentCopyVisibility
data class LinkPreview internal constructor(
    val title: String?,
    val description: String?,
    val image: String?,
    val url: String?,
    val link: String?
) {
    companion object {
        fun empty(link: String) = LinkPreview(
            link = link,
            image = null,
            title = null,
            description = null,
            url = null
        )
    }
}

suspend fun fetchLinkPreview(
    link: String
): LinkPreview = linksCache.call(link) {
    withContext(Dispatchers.IO) {
        var image: String? = null
        var title: String? = null
        var description: String? = null
        var url: String? = null


        runSuspendCatching {
            withTimeoutOrNull(30.seconds) {
                Jsoup
                    .connect(link)
                    .userAgent(USER_AGENT)
                    .execute()
                    .parse()
                    .getElementsByTag("meta")
                    .forEach { element ->
                        when (element.attr("property")) {
                            "og:image" -> image = element.attr("content")
                            "og:title" -> title = element.attr("content")
                            "og:description" -> description = element.attr("content")
                            "og:url" -> url = element.attr("content")
                        }
                    }
            }
        }


        LinkPreview(
            link = link,
            image = image,
            title = title,
            description = description,
            url = url
        )
    }
}

private val linksCache = Cache<String, LinkPreview>(1.minutes)