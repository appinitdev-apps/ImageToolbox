/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.crash.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.TELEGRAM_GROUP_LINK
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Github
import com.t8rin.imagetoolbox.core.resources.icons.MobileShare
import com.t8rin.imagetoolbox.core.resources.icons.Telegram
import com.t8rin.imagetoolbox.core.ui.theme.Black
import com.t8rin.imagetoolbox.core.ui.theme.Blue
import com.t8rin.imagetoolbox.core.ui.theme.White
import com.t8rin.imagetoolbox.core.ui.theme.outlineVariant
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.text.AutoSizeText

@Composable
internal fun CrashActionButtons(
    onCopyCrashInfo: () -> Unit,
    onShareLogs: () -> Unit,
    githubLink: String
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val containerWidth = maxWidth
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val linkHandler = LocalUriHandler.current
                LargeEnhancedButton(
                    onClick = {
                        onCopyCrashInfo()
                        linkHandler.openUri(TELEGRAM_GROUP_LINK)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .width(containerWidth / 2f)
                        .padding(end = 8.dp),
                    containerColor = Blue,
                    contentColor = White,
                    icon = Icons.Rounded.Telegram,
                    text = stringResource(R.string.contact_me)
                )
                LargeEnhancedButton(
                    onClick = {
                        onCopyCrashInfo()
                        linkHandler.openUri(githubLink)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .width(containerWidth / 2f),
                    containerColor = Black,
                    contentColor = White,
                    icon = Icons.Rounded.Github,
                    text = stringResource(id = R.string.create_issue),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            LargeEnhancedButton(
                onClick = {
                    onCopyCrashInfo()
                    onShareLogs()
                },
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                icon = Icons.Rounded.MobileShare,
                text = stringResource(id = R.string.send_logs),
            )
        }
    }
}

@Composable
private fun LargeEnhancedButton(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    EnhancedButton(
        onClick = onClick,
        modifier = modifier
            .height(48.dp),
        containerColor = containerColor,
        contentColor = contentColor,
        borderColor = MaterialTheme.colorScheme.outlineVariant(
            onTopOf = containerColor
        ),
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text
            )
            Spacer(modifier = Modifier.width(8.dp))
            AutoSizeText(
                text = text,
                maxLines = 1
            )
        }
    }
}