/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.draw.presentation.components.element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.colors.util.roundToTwoDigits
import com.t8rin.imagetoolbox.core.domain.model.Pt
import com.t8rin.imagetoolbox.core.domain.model.coerceIn
import com.t8rin.imagetoolbox.core.domain.model.pt
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.ImageSelector
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.feature.draw.domain.DrawMode

@Composable
internal fun ImageParamsSelector(
    value: DrawMode,
    onValueChange: (DrawMode) -> Unit,
    strokeWidth: Pt
) {
    AnimatedVisibility(
        visible = value is DrawMode.Image,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column {
            ImageSelector(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                value = (value as? DrawMode.Image)?.imageData ?: "",
                onValueChange = {
                    onValueChange(
                        (value as? DrawMode.Image)?.copy(
                            imageData = it
                        ) ?: value
                    )
                },
                subtitle = stringResource(id = R.string.draw_image_sub),
                shape = ShapeDefaults.top,
                color = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.height(4.dp))
            val dashMinimum = -((strokeWidth.value * 0.9f) / 2).toInt().toFloat()
            LaunchedEffect(dashMinimum, value) {
                if (value is DrawMode.Image && value.repeatingInterval < dashMinimum.pt) {
                    onValueChange(
                        value.copy(
                            repeatingInterval = value.repeatingInterval.coerceIn(
                                dashMinimum.pt,
                                100.pt
                            )
                        )
                    )
                }
            }
            EnhancedSliderItem(
                value = (value as? DrawMode.Image)?.repeatingInterval?.value ?: 0f,
                title = stringResource(R.string.dash_size),
                valueRange = dashMinimum..100f,
                internalStateTransformation = {
                    it.roundToTwoDigits()
                },
                onValueChange = {
                    onValueChange(
                        (value as? DrawMode.Image)?.copy(
                            repeatingInterval = it.pt.coerceIn(dashMinimum.pt, 100.pt)
                        ) ?: value
                    )
                },
                containerColor = MaterialTheme.colorScheme.surface,
                valueSuffix = " Pt",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = ShapeDefaults.bottom
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}