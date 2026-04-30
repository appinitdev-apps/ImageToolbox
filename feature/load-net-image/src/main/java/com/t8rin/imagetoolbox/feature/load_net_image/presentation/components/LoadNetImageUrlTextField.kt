/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.load_net_image.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Cancel
import com.t8rin.imagetoolbox.core.resources.icons.WifiTetheringError
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField
import com.t8rin.imagetoolbox.feature.load_net_image.presentation.screenLogic.LoadNetImageComponent

@Composable
internal fun LoadNetImageUrlTextField(
    component: LoadNetImageComponent
) {
    RoundedTextField(
        modifier = Modifier
            .container(
                shape = MaterialTheme.shapes.large,
                resultPadding = 8.dp
            ),
        value = component.targetUrl,
        onValueChange = {
            component.updateTargetUrl(
                newUrl = it,
                onFailure = {
                    AppToastHost.showToast(
                        message = it,
                        icon = Icons.Rounded.WifiTetheringError
                    )
                }
            )
        },
        singleLine = false,
        label = {
            Text(stringResource(id = R.string.image_link))
        },
        endIcon = {
            AnimatedVisibility(component.targetUrl.isNotBlank()) {
                EnhancedIconButton(
                    onClick = {
                        component.updateTargetUrl("")
                    },
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = stringResource(R.string.cancel)
                    )
                }
            }
        }
    )
}