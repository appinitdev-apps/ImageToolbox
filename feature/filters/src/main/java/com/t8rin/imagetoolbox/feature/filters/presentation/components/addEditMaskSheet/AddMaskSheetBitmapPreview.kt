/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.presentation.components.addEditMaskSheet

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.model.Pt
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedLoadingIndicator
import com.t8rin.imagetoolbox.core.ui.widget.image.ImageHeaderState
import com.t8rin.imagetoolbox.core.ui.widget.modifier.CornerSides
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.only
import com.t8rin.imagetoolbox.feature.draw.domain.DrawMode
import com.t8rin.imagetoolbox.feature.draw.presentation.components.BitmapDrawer
import net.engawapg.lib.zoomable.rememberZoomState

@Composable
internal fun AddMaskSheetBitmapPreview(
    component: AddMaskSheetComponent,
    imageState: ImageHeaderState,
    strokeWidth: Pt,
    brushSoftness: Pt,
    isEraserOn: Boolean,
    panEnabled: Boolean
) {
    val zoomState = rememberZoomState(maxScale = 30f, key = imageState)
    val isPortrait by isPortraitOrientationAsState()

    AnimatedContent(
        targetState = Triple(
            first = remember(component.previewBitmap) {
                derivedStateOf {
                    component.previewBitmap?.asImageBitmap()
                }
            }.value,
            second = component.maskPreviewModeEnabled,
            third = component.isImageLoading
        ),
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        modifier = Modifier
            .fillMaxSize()
            .clip(
                if (isPortrait) {
                    ShapeDefaults.extraLarge.only(
                        CornerSides.Bottom
                    )
                } else RectangleShape
            )
            .background(
                color = MaterialTheme.colorScheme
                    .surfaceContainer
                    .copy(0.8f)
            )
    ) { (imageBitmap, preview, loading) ->
        if (loading || imageBitmap == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                EnhancedLoadingIndicator()
            }
        } else {
            val aspectRatio = imageBitmap.width / imageBitmap.height.toFloat()
            var drawing by remember { mutableStateOf(false) }
            BitmapDrawer(
                zoomState = zoomState,
                imageBitmap = imageBitmap,
                paths = if (!preview || drawing || component.isImageLoading) component.paths else emptyList(),
                strokeWidth = strokeWidth,
                brushSoftness = brushSoftness,
                drawColor = component.maskColor,
                onAddPath = component::addPath,
                isEraserOn = isEraserOn,
                drawMode = DrawMode.Pen,
                modifier = Modifier
                    .padding(16.dp)
                    .aspectRatio(aspectRatio, isPortrait)
                    .fillMaxSize(),
                panEnabled = panEnabled,
                onDrawStart = {
                    drawing = true
                },
                onDrawFinish = {
                    drawing = false
                },
                onRequestFiltering = component::filter,
                drawPathMode = component.drawPathMode,
                backgroundColor = Color.Transparent
            )
        }
    }
}