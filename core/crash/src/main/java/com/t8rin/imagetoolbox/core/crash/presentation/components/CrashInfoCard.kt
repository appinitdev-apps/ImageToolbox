/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.crash.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.icons.BugReport
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.other.ExpandableItem
import com.t8rin.imagetoolbox.core.ui.widget.text.AutoSizeText

@Composable
internal fun CrashInfoCard(crashInfo: CrashInfo) {
    ExpandableItem(
        shape = ShapeDefaults.extraLarge,
        modifier = Modifier.fillMaxWidth(),
        visibleContent = {
            Icon(
                imageVector = Icons.Rounded.BugReport,
                contentDescription = "crash",
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
            )
            AutoSizeText(
                text = crashInfo.exceptionName,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )
        },
        expandableContent = {
            AnimatedVisibility(visible = it) {
                SelectionContainer {
                    Text(
                        text = crashInfo.stackTrace,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}