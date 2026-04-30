/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.gradient_maker.presentation.components

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.zIndex
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.ui.widget.image.Picture
import com.t8rin.imagetoolbox.core.ui.widget.modifier.meshGradient
import com.t8rin.imagetoolbox.core.ui.widget.modifier.transparencyChecker

@Composable
internal fun MeshGradientPreview(
    meshGradientState: UiMeshGradientState,
    gradientAlpha: Float,
    allowPickingImage: Boolean?,
    gradientSize: IntegerSize,
    imageAspectRatio: Float,
    selectedUri: Uri
) {
    val alpha by animateFloatAsState(gradientAlpha)
    AnimatedContent(
        targetState = if (allowPickingImage == true) {
            imageAspectRatio
        } else {
            gradientSize
                .aspectRatio
                .roundToTwoDigits()
                .coerceIn(0.01f..100f)
        }
    ) { aspectRatio ->
        Box {
            Spacer(
                modifier = Modifier
                    .aspectRatio(aspectRatio)
                    .clip(MaterialTheme.shapes.medium)
                    .then(
                        if (allowPickingImage != true) {
                            Modifier.transparencyChecker()
                        } else Modifier
                    )
                    .meshGradient(
                        points = meshGradientState.points,
                        resolutionX = meshGradientState.resolutionX,
                        resolutionY = meshGradientState.resolutionY,
                        alpha = alpha
                    )
                    .zIndex(2f)
            )
            if (allowPickingImage == true) {
                Picture(
                    model = selectedUri,
                    modifier = Modifier.matchParentSize(),
                    shape = MaterialTheme.shapes.medium,
                    size = 1500
                )
            }
        }
    }
}