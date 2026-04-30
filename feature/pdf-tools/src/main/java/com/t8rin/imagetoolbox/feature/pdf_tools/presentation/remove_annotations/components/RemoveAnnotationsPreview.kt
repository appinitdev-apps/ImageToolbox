/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.presentation.remove_annotations.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.data.coil.PdfImageRequest
import com.t8rin.imagetoolbox.core.ui.utils.helper.ImageUtils.safeAspectRatio
import com.t8rin.imagetoolbox.core.ui.widget.image.Picture
import com.t8rin.imagetoolbox.core.ui.widget.modifier.animateContentSizeNoClip
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.feature.pdf_tools.domain.model.PdfRemoveAnnotationParams
import com.t8rin.imagetoolbox.feature.pdf_tools.presentation.common.PageSwitcher

@Composable
internal fun RemoveAnnotationsPreview(
    uri: Uri?,
    params: PdfRemoveAnnotationParams,
    pageCount: Int
) {
    PageSwitcher(
        activePages = params.pages,
        pageCount = pageCount
    ) { page ->
        Box(
            modifier = Modifier
                .container()
                .padding(4.dp)
                .animateContentSizeNoClip(
                    alignment = Alignment.Center
                ),
            contentAlignment = Alignment.Center
        ) {
            var aspectRatio by rememberSaveable {
                mutableFloatStateOf(1f)
            }

            Box(
                modifier = Modifier
                    .aspectRatio(aspectRatio)
                    .clip(MaterialTheme.shapes.small)
            ) {
                Picture(
                    model = remember(uri, page) {
                        PdfImageRequest(
                            data = uri,
                            pdfPage = page
                        )
                    },
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize(),
                    onSuccess = {
                        aspectRatio = it.result.image.safeAspectRatio
                    },
                    shape = RectangleShape
                )
            }
        }
    }
}