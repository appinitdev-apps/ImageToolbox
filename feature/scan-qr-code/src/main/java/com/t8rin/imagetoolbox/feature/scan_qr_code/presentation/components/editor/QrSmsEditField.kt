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
import com.t8rin.imagetoolbox.core.resources.icons.HashTag
import com.t8rin.imagetoolbox.core.resources.icons.NoteSticky
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.ContactPickerButton
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField

@Composable
internal fun QrSmsEditField(
    value: QrType.Sms,
    onValueChange: (QrType.Sms) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RoundedTextField(
            value = value.phoneNumber,
            onValueChange = { onValueChange(value.copy(phoneNumber = it)) },
            label = { Text(stringResource(R.string.phone)) },
            startIcon = {
                Icon(
                    imageVector = Icons.Rounded.HashTag,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        RoundedTextField(
            value = value.message,
            onValueChange = { onValueChange(value.copy(message = it)) },
            label = { Text(stringResource(R.string.message)) },
            startIcon = {
                Icon(
                    imageVector = Icons.Outlined.NoteSticky,
                    contentDescription = null
                )
            },
            singleLine = false
        )

        ContactPickerButton(
            onPicked = { onValueChange(value.copy(phoneNumber = it.phones.firstOrNull()?.number.orEmpty())) }
        )
    }
}