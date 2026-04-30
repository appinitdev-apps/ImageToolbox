/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Done
import com.t8rin.imagetoolbox.core.resources.icons.ImageReset
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.utils.getString

@Composable
fun ResetDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    onReset: () -> Unit,
    title: String = stringResource(R.string.reset_image),
    text: String = stringResource(R.string.reset_image_sub),
    icon: ImageVector = Icons.Rounded.ImageReset
) {
    EnhancedAlertDialog(
        visible = visible,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = title
            )
        },
        title = { Text(title) },
        text = {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            EnhancedButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.close))
            }
        },
        dismissButton = {
            EnhancedButton(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    onReset()
                    onDismiss()
                    AppToastHost.showToast(
                        message = getString(R.string.values_reset),
                        icon = Icons.Rounded.Done
                    )
                }
            ) {
                Text(stringResource(R.string.reset))
            }
        }
    )
}