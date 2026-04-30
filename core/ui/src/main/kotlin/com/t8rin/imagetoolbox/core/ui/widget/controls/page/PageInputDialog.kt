/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.page

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Pages
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton

@Composable
fun PageInputDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    value: List<Int>?,
    onValueChange: (List<Int>) -> Unit,
    pagesCount: Int
) {
    var pages by rememberSaveable(visible) {
        mutableStateOf(value ?: emptyList())
    }
    EnhancedAlertDialog(
        visible = visible,
        onDismissRequest = onDismiss,
        title = {
            Text(stringResource(R.string.pages_selection))
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Pages,
                contentDescription = null
            )
        },
        text = {
            PageInputField(
                selectedPages = pages,
                onPagesChanged = { pages = it }
            )
        },
        dismissButton = {
            EnhancedButton(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.close))
            }
        },
        confirmButton = {
            EnhancedButton(
                onClick = {
                    onValueChange(pages.filter { it < pagesCount })
                    onDismiss()
                }
            ) {
                Text(stringResource(R.string.apply))
            }
        }
    )
}