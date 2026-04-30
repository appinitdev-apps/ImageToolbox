/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.palette.coders

import com.t8rin.palette.ColorSpace
import com.t8rin.palette.Palette
import com.t8rin.palette.PaletteCoder
import com.t8rin.palette.PaletteCoderException
import com.t8rin.palette.PaletteColor
import com.t8rin.palette.utils.readText
import java.io.InputStream
import java.io.OutputStream
import kotlin.math.roundToInt

/**
 * GIMP Palette (GPL) coder
 */
class GIMPPaletteCoder : PaletteCoder {
    companion object {
        private val nameRegex = Regex("^Name:\\s*(.*)$", RegexOption.IGNORE_CASE)
        private val colorRegex = Regex("^\\s*(\\d{1,3})\\s+(\\d{1,3})\\s+(\\d{1,3})(?:\\s+(.*))?$")
    }

    override fun decode(input: InputStream): Palette {
        val text = input.readText()
        val lines = text.lines()

        val header = lines.firstOrNull()?.removePrefix("\uFEFF")?.trim() ?: ""
        if (!header.equals("GIMP Palette", ignoreCase = true)) {
            throw PaletteCoderException.InvalidFormat()
        }

        val result = Palette.Builder()

        for (line in lines.drop(1)) {
            val trimmed = line.trim()
            if (trimmed.isEmpty()) continue
            if (trimmed.startsWith("#")) continue
            if (trimmed.startsWith("Columns:", ignoreCase = true)) continue

            // Check for name
            nameRegex.find(trimmed)?.let { match ->
                result.name = match.groupValues[1].trim()
                continue
            }

            // Check for color
            colorRegex.find(trimmed)?.let { match ->
                val r = match.groupValues[1].toIntOrNull() ?: continue
                val g = match.groupValues[2].toIntOrNull() ?: continue
                val b = match.groupValues[3].toIntOrNull() ?: continue
                if (r !in 0..255 || g !in 0..255 || b !in 0..255) continue

                val name = match.groupValues.getOrElse(4) { "" }.trim()

                val color = PaletteColor.rgb(
                    r = (r / 255.0).coerceIn(0.0, 1.0),
                    g = (g / 255.0).coerceIn(0.0, 1.0),
                    b = (b / 255.0).coerceIn(0.0, 1.0),
                    name = name
                )
                result.colors.add(color)
            }
        }

        return result.build()
    }

    override fun encode(palette: Palette, output: OutputStream) {
        val originalColors = palette.allColors()
        val result = buildString {
            appendLine("GIMP Palette")
            appendLine("Name: ${sanitizeGimpText(palette.name)}")
            appendLine("Columns: 0")
            appendLine("#Colors: ${originalColors.size}")

            originalColors.forEach { color ->
                val rgb = if (color.colorSpace == ColorSpace.RGB) color.toRgb()
                else color.converted(ColorSpace.RGB).toRgb()

                val r = (rgb.rf * 255.0).roundToInt().coerceIn(0, 255)
                val g = (rgb.gf * 255.0).roundToInt().coerceIn(0, 255)
                val b = (rgb.bf * 255.0).roundToInt().coerceIn(0, 255)

                append("$r\t$g\t$b")
                val colorName = sanitizeGimpText(color.name)
                if (colorName.isNotEmpty()) {
                    append('\t')
                    append(colorName)
                }
                append('\n')
            }
        }

        output.write(result.toByteArray(java.nio.charset.StandardCharsets.UTF_8))
    }

    private fun sanitizeGimpText(value: String): String =
        value.replace(Regex("[\\r\\n\\t]+"), " ").trim()
}