/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.crash.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ContentCopy
import com.t8rin.imagetoolbox.core.resources.icons.RestartAlt
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedFloatingActionButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedFloatingActionButtonType
import com.t8rin.imagetoolbox.core.ui.widget.text.AutoSizeText

@Composable
internal fun CrashBottomButtons(
    modifier: Modifier,
    onCopy: () -> Unit,
    onRestartApp: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .navigationBarsPadding()
            .displayCutoutPadding()
    ) {
        EnhancedFloatingActionButton(
            modifier = Modifier
                .weight(1f, false),
            onClick = onRestartApp,
            content = {
                Spacer(Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Rounded.RestartAlt,
                    contentDescription = stringResource(R.string.restart_app)
                )
                Spacer(Modifier.width(16.dp))
                AutoSizeText(
                    text = stringResource(R.string.restart_app),
                    maxLines = 1
                )
                Spacer(Modifier.width(16.dp))
            }
        )
        Spacer(Modifier.width(8.dp))
        EnhancedFloatingActionButton(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            onClick = onCopy,
            type = EnhancedFloatingActionButtonType.SecondaryHorizontal
        ) {
            Icon(
                imageVector = Icons.Rounded.ContentCopy,
                contentDescription = stringResource(R.string.copy)
            )
        }
    }
}