/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.compare.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ArrowBack
import com.t8rin.imagetoolbox.core.resources.icons.Label
import com.t8rin.imagetoolbox.core.resources.icons.RotateLeft
import com.t8rin.imagetoolbox.core.resources.icons.RotateRight
import com.t8rin.imagetoolbox.core.resources.icons.SwapHoriz
import com.t8rin.imagetoolbox.core.ui.widget.buttons.ShareButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedTopAppBar
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedTopAppBarType
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee

@Composable
fun CompareScreenTopAppBar(
    imageNotPicked: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigationIconClick: () -> Unit,
    onShareButtonClick: () -> Unit,
    onSwapImagesClick: () -> Unit,
    onRotateImagesClick: () -> Unit,
    isShareButtonVisible: Boolean,
    isImagesRotated: Boolean,
    titleWhenBitmapsPicked: String,
    onToggleLabelsEnabled: (Boolean) -> Unit,
    isLabelsEnabled: Boolean,
    isLabelsButtonVisible: Boolean
) {
    if (imageNotPicked) {
        EnhancedTopAppBar(
            type = EnhancedTopAppBarType.Large,
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                EnhancedIconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(R.string.exit)
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.compare),
                    modifier = Modifier.marquee()
                )
            },
            actions = {
                TopAppBarEmoji()
            }
        )
    } else {
        EnhancedTopAppBar(
            navigationIcon = {
                EnhancedIconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(R.string.exit)
                    )
                }
            },
            actions = {
                AnimatedVisibility(visible = isShareButtonVisible) {
                    ShareButton(onShare = onShareButtonClick)
                }
                EnhancedIconButton(
                    onClick = onSwapImagesClick
                ) {
                    Icon(
                        imageVector = Icons.Rounded.SwapHoriz,
                        contentDescription = "Swap"
                    )
                }
                EnhancedIconButton(
                    onClick = onRotateImagesClick
                ) {
                    AnimatedContent(isImagesRotated) { rotated ->
                        Icon(
                            imageVector = if (rotated) Icons.Rounded.RotateLeft
                            else Icons.Rounded.RotateRight,
                            contentDescription = "Rotate"
                        )
                    }
                }
                AnimatedVisibility(visible = isLabelsButtonVisible) {
                    EnhancedIconButton(
                        onClick = {
                            onToggleLabelsEnabled(!isLabelsEnabled)
                        },
                        containerColor = animateColorAsState(
                            if (isLabelsEnabled) MaterialTheme.colorScheme.secondary
                            else Color.Transparent
                        ).value
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Label,
                            contentDescription = "Label"
                        )
                    }
                }
            },
            title = {
                AnimatedContent(
                    targetState = titleWhenBitmapsPicked,
                    modifier = Modifier.marquee()
                ) { text ->
                    Text(text)
                }
            }
        )
    }
}