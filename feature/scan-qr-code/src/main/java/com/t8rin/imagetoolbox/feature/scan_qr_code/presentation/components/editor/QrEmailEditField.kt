/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.scan_qr_code.presentation.components.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.model.QrType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.AlternateEmail
import com.t8rin.imagetoolbox.core.resources.icons.ShortText
import com.t8rin.imagetoolbox.core.resources.icons.Topic
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField

@Composable
internal fun QrEmailEditField(
    value: QrType.Email,
    onValueChange: (QrType.Email) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RoundedTextField(
            value = value.address,
            onValueChange = { onValueChange(value.copy(address = it)) },
            label = { Text(stringResource(R.string.address)) },
            startIcon = {
                Icon(
                    imageVector = Icons.Rounded.AlternateEmail,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        RoundedTextField(
            value = value.subject,
            onValueChange = { onValueChange(value.copy(subject = it)) },
            label = { Text(stringResource(R.string.subject)) },
            startIcon = {
                Icon(
                    imageVector = Icons.Outlined.Topic,
                    contentDescription = null
                )
            },
            singleLine = false
        )
        RoundedTextField(
            value = value.body,
            onValueChange = { onValueChange(value.copy(body = it)) },
            label = { Text(stringResource(R.string.body)) },
            startIcon = {
                Icon(
                    imageVector = Icons.Rounded.ShortText,
                    contentDescription = null
                )
            },
            singleLine = false
        )
    }
}