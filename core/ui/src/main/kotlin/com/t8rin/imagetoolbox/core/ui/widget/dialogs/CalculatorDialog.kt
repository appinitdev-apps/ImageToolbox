/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.github.keelar.exprk.Expressions
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Calculate
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedAlertDialog
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import java.math.BigDecimal

@Composable
fun CalculatorDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    initialValue: BigDecimal?,
    onValueChange: (BigDecimal) -> Unit
) {
    var calculatorExpression by rememberSaveable(initialValue, visible) {
        mutableStateOf(initialValue?.toString() ?: "")
    }
    EnhancedAlertDialog(
        visible = visible,
        onDismissRequest = onDismiss,
        confirmButton = {
            EnhancedButton(
                onClick = {
                    runCatching {
                        Expressions().eval(calculatorExpression)
                    }.onFailure {
                        AppToastHost.showFailureToast(it)
                    }.onSuccess {
                        onValueChange(it)
                        onDismiss()
                    }
                }
            ) {
                Text(stringResource(R.string.apply))
            }
        },
        title = {
            Text(
                text = stringResource(R.string.calculate)
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Calculate,
                contentDescription = null
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
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    shape = ShapeDefaults.default,
                    value = calculatorExpression,
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    ),
                    maxLines = 1,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.math_expression),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onValueChange = { expr ->
                        calculatorExpression = expr.filter { !it.isWhitespace() }
                    },
                    supportingText = {
                        Text(stringResource(R.string.calculate_hint))
                    }
                )
            }
        }
    )
}