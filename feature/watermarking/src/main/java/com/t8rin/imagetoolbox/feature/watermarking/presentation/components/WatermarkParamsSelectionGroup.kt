/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.watermarking.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Tune
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.other.ExpandableItem
import com.t8rin.imagetoolbox.core.ui.widget.text.TitleItem
import com.t8rin.imagetoolbox.feature.watermarking.domain.WatermarkParams
import com.t8rin.imagetoolbox.feature.watermarking.presentation.components.selectors.CommonParamsContent
import com.t8rin.imagetoolbox.feature.watermarking.presentation.components.selectors.DigitalParamsContent
import com.t8rin.imagetoolbox.feature.watermarking.presentation.components.selectors.ImageParamsContent
import com.t8rin.imagetoolbox.feature.watermarking.presentation.components.selectors.StampParamsContent
import com.t8rin.imagetoolbox.feature.watermarking.presentation.components.selectors.TextParamsContent

@Composable
fun WatermarkParamsSelectionGroup(
    value: WatermarkParams,
    onValueChange: (WatermarkParams) -> Unit,
    modifier: Modifier = Modifier
) {
    ExpandableItem(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainer,
        visibleContent = {
            TitleItem(
                text = stringResource(id = R.string.properties),
                icon = Icons.Rounded.Tune
            )
        },
        expandableContent = {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val params by rememberUpdatedState(value)

                CommonParamsContent(
                    params = params,
                    onValueChange = onValueChange
                )

                TextParamsContent(
                    params = params,
                    onValueChange = onValueChange
                )

                ImageParamsContent(
                    params = params,
                    onValueChange = onValueChange
                )

                DigitalParamsContent(
                    params = params,
                    onValueChange = onValueChange
                )

                StampParamsContent(
                    params = params,
                    onValueChange = onValueChange
                )
            }
        },
        shape = ShapeDefaults.extraLarge
    )
}