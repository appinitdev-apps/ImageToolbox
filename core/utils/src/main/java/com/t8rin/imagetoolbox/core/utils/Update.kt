/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.utils

import com.t8rin.imagetoolbox.core.domain.utils.cast
import com.t8rin.imagetoolbox.core.resources.BuildConfig
import org.w3c.dom.Element
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

fun isNeedUpdate(
    updateName: String,
    allowBetas: Boolean
): Boolean {
    val currentName = BuildConfig.VERSION_NAME
    val betaList = listOf(
        "alpha", "beta", "rc"
    )

    val currentVersionCodeString = currentName.toVersionCodeString(betaList)
    val updateVersionCodeString = updateName.toVersionCodeString(betaList)

    val maxLength = maxOf(currentVersionCodeString.length, updateVersionCodeString.length)

    val currentVersionCode = currentVersionCodeString.padEnd(maxLength, '0').toIntOrNull() ?: -1
    val updateVersionCode = updateVersionCodeString.padEnd(maxLength, '0').toIntOrNull() ?: -1

    return if (!updateName.startsWith(currentName)) {
        if (betaList.all { it !in updateName }) {
            updateVersionCode > currentVersionCode
        } else {
            if (allowBetas || betaList.any { it in currentName }) {
                updateVersionCode > currentVersionCode
            } else false
        }
    } else false
}

fun InputStream.parseChangelog(): Changelog {
    var tag = ""
    var changelog = ""

    val tree = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder().parse(this)
        .getElementsByTagName("feed")

    repeat(tree.length) {
        val line = tree.item(it).cast<Element>()
            .getElementsByTagName("entry").item(0)
            .cast<Element>()

        tag = line.getElementsByTagName("title").item(0).textContent
        changelog = line.getElementsByTagName("content").item(0).textContent
    }

    return Changelog(
        tag = tag,
        changelog = changelog
    )
}

data class Changelog(
    val tag: String,
    val changelog: String
)

private fun String.toVersionCodeString(betaList: List<String>): String {
    return replace(
        regex = Regex("0\\d"),
        transform = {
            it.value.replace("0", "")
        }
    ).replace("-", "")
        .replace(".", "")
        .replace("_", "")
        .let { version ->
            if (betaList.any { it in version }) version
            else version + "4"
        }
        .replace("alpha", "1")
        .replace("beta", "2")
        .replace("rc", "3")
        .replace("foss", "")
        .replace("jxl", "")
}