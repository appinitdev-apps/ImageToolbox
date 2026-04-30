/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.watermarking.presentation.components.selectors

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.feature.watermarking.domain.WatermarkParams
import com.t8rin.imagetoolbox.feature.watermarking.domain.WatermarkingType
import com.t8rin.imagetoolbox.feature.watermarking.domain.digitalParams

@Composable
internal fun ImageParamsContent(
    params: WatermarkParams,
    onValueChange: (WatermarkParams) -> Unit
) {
    val digitalParams = params.watermarkingType.digitalParams()
    val isInvisible = digitalParams?.isInvisible == true

    AnimatedVisibility(
        visible = params.watermarkingType is WatermarkingType.Image && !isInvisible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        val type = params.watermarkingType as? WatermarkingType.Image
            ?: return@AnimatedVisibility

        EnhancedSliderItem(
            value = type.size,
            title = stringResource(R.string.watermark_size),
            internalStateTransformation = {
                it.roundToTwoDigits()
            },
            onValueChange = {
                onValueChange(
                    params.copy(
                        watermarkingType = type.copy(size = it)
                    )
                )
            },
            valueRange = 0.01f..1f,
            shape = ShapeDefaults.large,
            containerColor = MaterialTheme.colorScheme.surface
        )
    }
}