/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.watermarking.presentation.components.selectors

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.DisabledVisible
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import com.t8rin.imagetoolbox.feature.watermarking.domain.WatermarkParams
import com.t8rin.imagetoolbox.feature.watermarking.domain.copy
import com.t8rin.imagetoolbox.feature.watermarking.domain.digitalParams
import com.t8rin.imagetoolbox.feature.watermarking.domain.isStamp

@Composable
internal fun DigitalParamsContent(
    params: WatermarkParams,
    onValueChange: (WatermarkParams) -> Unit
) {
    val digitalParams = params.watermarkingType.digitalParams()
    val isInvisible = digitalParams?.isInvisible == true

    AnimatedVisibility(
        visible = !params.watermarkingType.isStamp(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column {
            PreferenceRowSwitch(
                title = stringResource(id = R.string.invisible_mode),
                subtitle = stringResource(id = R.string.invisible_mode_sub),
                checked = isInvisible,
                startIcon = Icons.Rounded.DisabledVisible,
                onClick = {
                    onValueChange(
                        params.copy(
                            digitalParams = digitalParams?.copy(
                                isInvisible = !isInvisible
                            )
                        )
                    )
                },
                shape = ShapeDefaults.large,
                containerColor = MaterialTheme.colorScheme.surface
            )
//            AnimatedVisibility(visible = isInvisible) {
//                PreferenceRowSwitch(
//                    title = stringResource(id = R.string.use_lsb),
//                    subtitle = stringResource(id = R.string.use_lsb_sub),
//                    checked = digitalParams?.isLSB ?: false,
//                    startIcon = Icons.Rounded.GraphicEq,
//                    onClick = {
//                        onValueChange(
//                            params.copy(
//                                digitalParams = digitalParams?.copy(
//                                    isLSB = !digitalParams.isLSB
//                                )
//                            )
//                        )
//                    },
//                    shape = ShapeDefaults.large,
//                    containerColor = MaterialTheme.colorScheme.surface,
//                    modifier = Modifier.padding(top = 4.dp)
//                )
//            }
        }
    }
}