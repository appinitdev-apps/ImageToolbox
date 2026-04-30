/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.t8rin.imagetoolbox.core.settings.presentation.model.UiFontFamily

@Composable
fun rememberTypography(
    fontRes: UiFontFamily
): Typography = remember(fontRes) {
    derivedStateOf {
        Typography(
            displayLarge = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 57.sp,
                lineHeight = 64.sp,
                letterSpacing = (-0.25).sp,
                fontSynthesis = FontSynthesis.All
            ),
            displayMedium = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 45.sp,
                lineHeight = 52.sp,
                letterSpacing = 0.sp,
                fontSynthesis = FontSynthesis.All
            ),
            displaySmall = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                letterSpacing = 0.sp,
                fontSynthesis = FontSynthesis.All
            ),
            headlineLarge = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp,
                fontSynthesis = FontSynthesis.All
            ),
            headlineMedium = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp,
                fontSynthesis = FontSynthesis.All
            ),
            headlineSmall = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
                fontSynthesis = FontSynthesis.All
            ),
            titleLarge = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
                fontSynthesis = FontSynthesis.All
            ),
            titleMedium = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.1.sp,
                fontSynthesis = FontSynthesis.All
            ),
            titleSmall = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
                fontSynthesis = FontSynthesis.All
            ),
            bodyLarge = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                fontSynthesis = FontSynthesis.All
            ),
            bodyMedium = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center,
                fontSynthesis = FontSynthesis.All
            ),
            bodySmall = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                fontSynthesis = FontSynthesis.All
            ),
            labelLarge = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
                fontSynthesis = FontSynthesis.All
            ),
            labelMedium = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
                fontSynthesis = FontSynthesis.All
            ),
            labelSmall = TextStyle(
                fontFamily = fontRes.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                fontSynthesis = FontSynthesis.All
            ),
        )
    }
}.value

@Composable
fun ProvideTypography(
    fontRes: UiFontFamily,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = rememberTypography(fontRes),
        content = content
    )
}