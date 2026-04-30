/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.page

import androidx.compose.foundation.layout.fillMaxWidth
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.MiniEdit
import com.t8rin.imagetoolbox.core.resources.icons.Pages
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItem
import com.t8rin.imagetoolbox.core.utils.getString

@Composable
fun PageSelectionItem(
    value: List<Int>?,
    onValueChange: (List<Int>) -> Unit,
    pageCount: Int
) {
    var showSelector by rememberSaveable {
        mutableStateOf(false)
    }
    PreferenceItem(
        title = stringResource(R.string.pages_selection),
        subtitle = remember(value, pageCount) {
            derivedStateOf {
                value?.takeIf { it.isNotEmpty() }
                    ?.let {
                        if (it.size == pageCount) {
                            getString(R.string.all)
                        } else {
                            PagesSelectionParser.formatPageOutput(it)
                        }
                    } ?: getString(R.string.none)
            }
        }.value,
        onClick = {
            showSelector = true
        },
        modifier = Modifier.fillMaxWidth(),
        startIcon = Icons.Rounded.Pages,
        endIcon = Icons.Rounded.MiniEdit
    )
    PageInputDialog(
        visible = showSelector,
        onDismiss = { showSelector = false },
        value = value,
        onValueChange = onValueChange,
        pagesCount = pageCount
    )
}