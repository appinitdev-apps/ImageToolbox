/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.text

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.t8rin.imagetoolbox.core.domain.saving.model.FilenamePattern
import com.t8rin.imagetoolbox.core.ui.theme.ImageToolboxThemeForPreview
import com.t8rin.imagetoolbox.core.ui.theme.blend
import com.t8rin.imagetoolbox.core.ui.theme.takeColorFromScheme

data class PatternHighlightTransformation(
    private val mapping: Map<Regex, Color>
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val annotated = buildAnnotatedString {
            append(text)

            mapping.forEach { (re, color) ->
                re.findAll(text).forEach { match ->
                    addStyle(
                        style = SpanStyle(
                            color = color,
                            fontWeight = FontWeight.SemiBold,
                            background = color.copy(0.15f)
                        ),
                        start = match.range.first,
                        end = match.range.last + 1
                    )
                }
            }
        }

        return TransformedText(
            text = annotated,
            offsetMapping = OffsetMapping.Identity
        )
    }

    companion object {
        @Composable
        fun default(): PatternHighlightTransformation {
            val color = takeColorFromScheme {
                primary.blend(primaryContainer, 0.1f)
            }
            val colorUpper = takeColorFromScheme {
                tertiary.blend(tertiaryContainer, 0.1f)
            }

            return remember(color, colorUpper) {
                PatternHighlightTransformation(
                    mapOf(
                        PATTERN_TOKENS to color,
                        UPPER_PATTERN_TOKENS to colorUpper
                    )
                )
            }
        }
    }

}

private val PATTERN_TOKENS = Regex(
    """\\[pwdhrcoimse](\{[^}]*\})?"""
)

private val UPPER_PATTERN_TOKENS = Regex(
    """\\[PDOIMSE](\{[^}]*\})?"""
)

@Preview
@Composable
private fun Preview() = ImageToolboxThemeForPreview(
    isDarkTheme = true,
    keyColor = Color.Blue
) {
    TextField(
        value = FilenamePattern.Default + "\\E",
        onValueChange = {},
        visualTransformation = PatternHighlightTransformation.default()
    )
}

@Preview
@Composable
private fun Preview1() = ImageToolboxThemeForPreview(
    isDarkTheme = false,
    keyColor = Color.Blue
) {
    TextField(
        value = FilenamePattern.Default + "\\E",
        onValueChange = {},
        visualTransformation = PatternHighlightTransformation.default()
    )
}