/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.luminance
import androidx.palette.graphics.Palette
import com.t8rin.colors.model.ColorData
import com.t8rin.colors.parser.ColorNameParser
import com.t8rin.colors.util.ColorUtil

data class PaletteData(
    val colorData: ColorData,
    val percent: Float
)

@Composable
fun rememberImageColorPaletteState(
    imageBitmap: ImageBitmap,
    maximumColorCount: Int = 32,
): ImageColorPaletteState {
    return remember(imageBitmap, maximumColorCount) {
        derivedStateOf {
            ImageColorPaletteStateImpl(
                image = imageBitmap,
                maximumColorCount = maximumColorCount
            )
        }
    }.value
}

interface ImageColorPaletteState {
    val image: ImageBitmap
    val maximumColorCount: Int
    val paletteData: List<PaletteData>
}

private class ImageColorPaletteStateImpl(
    override val image: ImageBitmap,
    override val maximumColorCount: Int
) : ImageColorPaletteState {
    override val paletteData: List<PaletteData> = run {
        val paletteData = mutableListOf<PaletteData>()
        val palette = Palette
            .from(image.asAndroidBitmap())
            .maximumColorCount(maximumColorCount)
            .generate()


        val numberOfPixels: Float = palette.swatches.sumOf {
            it.population
        }.toFloat()

        palette.swatches.forEach { paletteSwatch ->
            paletteSwatch?.let { swatch ->
                val color = Color(swatch.rgb)
                val name = ColorNameParser.parseColorName(color)
                val colorData = ColorData(color, name)
                val percent: Float = swatch.population / numberOfPixels
                paletteData.add(PaletteData(colorData = colorData, percent))
            }
        }

        paletteData.distinctBy {
            it.colorData.name
        }.sortedWith(
            compareBy(
                { it.colorData.color.luminance() },
                { ColorUtil.colorToHSV(it.colorData.color)[0] },
            )
        )
    }
}