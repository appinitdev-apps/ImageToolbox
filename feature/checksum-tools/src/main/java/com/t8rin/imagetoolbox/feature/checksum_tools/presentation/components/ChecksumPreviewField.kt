/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ContentCopy
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextFieldColors

@Composable
internal fun ChecksumPreviewField(
    value: String,
    onCopyText: (String) -> Unit,
    label: String = stringResource(R.string.source_checksum)
) {
    RoundedTextField(
        modifier = Modifier
            .container(
                shape = MaterialTheme.shapes.large,
                resultPadding = 8.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer.copy(
                    0.2f
                )
            ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        onValueChange = {},
        singleLine = false,
        readOnly = true,
        value = value,
        endIcon = {
            AnimatedVisibility(value.isNotBlank()) {
                EnhancedIconButton(
                    onClick = {
                        onCopyText(value)
                    },
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ContentCopy,
                        contentDescription = stringResource(
                            R.string.copy
                        )
                    )
                }
            }
        },
        colors = RoundedTextFieldColors(
            isError = false,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(0.3f),
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(
                0.5f
            )
        ),
        label = label
    )
}