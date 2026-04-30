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
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.SquareFoot
import com.t8rin.imagetoolbox.core.ui.utils.helper.toColor
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceRowSwitch
import com.t8rin.imagetoolbox.feature.draw.domain.DrawPathMode
import com.t8rin.imagetoolbox.feature.draw.presentation.components.utils.isPolygon
import com.t8rin.imagetoolbox.feature.draw.presentation.components.utils.isRegular
import com.t8rin.imagetoolbox.feature.draw.presentation.components.utils.rotationDegrees
import com.t8rin.imagetoolbox.feature.draw.presentation.components.utils.updateOutlined
import com.t8rin.imagetoolbox.feature.draw.presentation.components.utils.updatePolygon
import com.t8rin.imagetoolbox.feature.draw.presentation.components.utils.vertices
import kotlin.math.roundToInt

@Composable
internal fun PolygonParamsSelector(
    value: DrawPathMode,
    onValueChange: (DrawPathMode) -> Unit,
    canChangeFillColor: Boolean
) {
    AnimatedVisibility(
        visible = value.isPolygon(),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column {
            AnimatedVisibility(
                visible = canChangeFillColor,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                OutlinedFillColorSelector(
                    value = value.outlinedFillColor?.toColor(),
                    onValueChange = {
                        onValueChange(value.updateOutlined(it))
                    },
                    shape = ShapeDefaults.top,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            }
            EnhancedSliderItem(
                value = value.vertices(),
                title = stringResource(R.string.vertices),
                valueRange = 3f..24f,
                steps = 20,
                internalStateTransformation = {
                    it.roundToInt()
                },
                onValueChange = {
                    onValueChange(
                        value.updatePolygon(vertices = it.toInt())
                    )
                },
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = if (canChangeFillColor) ShapeDefaults.center else ShapeDefaults.top
            )
            Spacer(modifier = Modifier.height(4.dp))
            EnhancedSliderItem(
                value = value.rotationDegrees(),
                title = stringResource(R.string.angle),
                valueRange = 0f..360f,
                internalStateTransformation = {
                    it.roundToInt()
                },
                onValueChange = {
                    onValueChange(
                        value.updatePolygon(rotationDegrees = it.toInt())
                    )
                },
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = ShapeDefaults.center
            )
            Spacer(modifier = Modifier.height(4.dp))
            PreferenceRowSwitch(
                title = stringResource(R.string.draw_regular_polygon),
                subtitle = stringResource(R.string.draw_regular_polygon_sub),
                checked = value.isRegular(),
                onClick = {
                    onValueChange(
                        value.updatePolygon(isRegular = it)
                    )
                },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = ShapeDefaults.bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                startIcon = Icons.Rounded.SquareFoot,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}