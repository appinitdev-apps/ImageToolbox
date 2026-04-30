/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.page

internal object PagesSelectionParser {

    fun parsePageInput(input: String): List<Int> {
        val pages = mutableSetOf<Int>()
        val regex = "\\d+(-\\d+)?".toRegex()
        regex.findAll(input).forEach { match ->
            val rangeParts = match.value.split("-").mapNotNull { it.toIntOrNull() }
            when (rangeParts.size) {
                1 -> pages.add(rangeParts[0] - 1)
                2 -> if (rangeParts[0] <= rangeParts[1]) {
                    pages.addAll((rangeParts[0] - 1)..<rangeParts[1])
                }
            }
        }
        return pages.sorted()
    }

    fun formatPageOutput(pages: List<Int>): String {
        if (pages.isEmpty()) return ""
        val pages = pages.sorted()
        val result = mutableListOf<String>()
        var start = pages[0]
        var prev = pages[0]
        for (i in 1 until pages.size) {
            if (pages[i] != prev + 1) {
                result.add(if (start == prev) "${start + 1}" else "${start + 1}-${prev + 1}")
                start = pages[i]
            }
            prev = pages[i]
        }
        result.add(if (start == prev) "${start + 1}" else "${start + 1}-${prev + 1}")
        return result.joinToString(", ")
    }

}