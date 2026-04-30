/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.recognize.text.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedBottomSheetDefaults
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.enhancedFlingBehavior
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem
import com.t8rin.imagetoolbox.feature.recognize.text.domain.OCRLanguage
import com.t8rin.imagetoolbox.feature.recognize.text.domain.RecognitionType

@Composable
internal fun OCRLanguageColumnForSearch(
    languagesForSearch: List<OCRLanguage>,
    value: List<OCRLanguage>,
    currentRecognitionType: RecognitionType,
    onValueChange: (List<OCRLanguage>, RecognitionType) -> Unit,
    allowMultipleLanguagesSelection: Boolean
) {
    fun onValueChangeImpl(
        selected: Boolean,
        type: RecognitionType,
        lang: OCRLanguage
    ) {
        if (allowMultipleLanguagesSelection) {
            if (selected) {
                onValueChange(
                    (value - lang).distinct(),
                    type
                )
            } else onValueChange(
                (value + lang).distinct(),
                type
            )
        } else onValueChange(listOf(lang), type)
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        flingBehavior = enhancedFlingBehavior()
    ) {
        itemsIndexed(
            items = languagesForSearch,
            key = { _, l -> l.code }
        ) { index, lang ->
            val selected by remember(value, lang) {
                derivedStateOf {
                    lang in value
                }
            }
            PreferenceItem(
                title = lang.name,
                subtitle = lang.localizedName.takeIf { it != lang.name },
                onClick = {
                    onValueChangeImpl(
                        selected = selected,
                        type = currentRecognitionType,
                        lang = lang
                    )
                },
                containerColor = animateColorAsState(
                    if (selected) {
                        MaterialTheme.colorScheme.surfaceColorAtElevation(
                            20.dp
                        )
                    } else EnhancedBottomSheetDefaults.contentContainerColor
                ).value,
                shape = ShapeDefaults.byIndex(
                    index = index,
                    size = languagesForSearch.size
                ),
                modifier = Modifier
                    .animateItem()
                    .fillMaxWidth()
            )
        }
    }
}