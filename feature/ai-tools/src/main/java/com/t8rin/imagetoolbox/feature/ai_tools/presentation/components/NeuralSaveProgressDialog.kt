/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.LoadingDialog
import com.t8rin.imagetoolbox.core.ui.widget.text.AutoSizeText
import com.t8rin.imagetoolbox.feature.ai_tools.presentation.screenLogic.AiToolsComponent

@Composable
internal fun NeuralSaveProgressDialog(
    component: AiToolsComponent
) {
    component.saveProgress?.let { saveProgress ->
        LoadingDialog(
            visible = true,
            onCancelLoading = component::cancelSaving,
            progress = { saveProgress.totalProgress },
            switchToIndicator = saveProgress.isZero,
            loaderSize = 72.dp,
            isLayoutSwappable = false,
            additionalContent = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AutoSizeText(
                        text = if (saveProgress.totalImages > 1) {
                            "${saveProgress.doneImages} / ${saveProgress.totalImages}"
                        } else {
                            "${saveProgress.doneChunks} / ${saveProgress.totalChunks}"
                        },
                        maxLines = 1,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.width(it * 0.7f),
                        textAlign = TextAlign.Center
                    )
                    if (saveProgress.totalImages > 1 && saveProgress.totalChunks > 0) {
                        AutoSizeText(
                            text = "${saveProgress.doneChunks} / ${saveProgress.totalChunks}",
                            maxLines = 1,
                            style = LocalTextStyle.current.copy(
                                fontSize = 12.sp,
                                lineHeight = 12.sp
                            ),
                            color = LocalContentColor.current.copy(0.5f),
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.width(it * 0.45f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        )
    }
}