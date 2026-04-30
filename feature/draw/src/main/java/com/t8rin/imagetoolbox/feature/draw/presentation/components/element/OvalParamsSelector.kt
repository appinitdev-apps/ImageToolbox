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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.ui.utils.helper.toColor
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.feature.draw.domain.DrawPathMode
import com.t8rin.imagetoolbox.feature.draw.presentation.components.utils.updateOutlined

@Composable
internal fun OvalParamsSelector(
    value: DrawPathMode,
    onValueChange: (DrawPathMode) -> Unit,
    canChangeFillColor: Boolean
) {
    AnimatedVisibility(
        visible = value is DrawPathMode.OutlinedOval && canChangeFillColor,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column {
            OutlinedFillColorSelector(
                value = value.outlinedFillColor?.toColor(),
                onValueChange = {
                    onValueChange(value.updateOutlined(it))
                },
                shape = ShapeDefaults.default,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                containerColor = MaterialTheme.colorScheme.surface,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}