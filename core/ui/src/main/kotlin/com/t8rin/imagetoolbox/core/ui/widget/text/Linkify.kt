/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString

fun AnnotatedString.linkify(
    linkStyles: TextLinkStyles
): AnnotatedString = buildAnnotatedString {
    append(this@linkify)

    val text = this@linkify.text
    val matches = URL_PATTERN.findAll(text).toList()

    for (match in matches) {
        val url = match.value

        if (url.contains("@") && !url.startsWith("mailto:")) {
            continue
        }

        val start = match.range.first
        val end = match.range.last + 1

        val hasLinkAnnotation = this@linkify.getStringAnnotations(
            tag = url,
            start = start,
            end = end
        ).any { it.item == url }

        if (!hasLinkAnnotation) {
            addLink(
                url = LinkAnnotation.Url(
                    url = url,
                    styles = linkStyles
                ),
                start = start,
                end = end
            )
        }
    }
}

private val URL_PATTERN =
    Regex("(https?://(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.\\S{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.\\S{2,}|https?://(?:www\\.|(?!www))[a-zA-Z0-9]+\\.\\S{2,}|www\\.[a-zA-Z0-9]+\\.\\S{2,})")