/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.widget.filterItem

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.filters.presentation.model.UiFilter
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Done
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField
import kotlin.math.absoluteValue

@Composable
internal fun FloatArrayItem(
    value: FloatArray,
    filter: UiFilter<FloatArray>,
    onFilterChange: (value: FloatArray) -> Unit,
    previewOnly: Boolean
) {
    val rows = filter.paramsInfo[0].valueRange.start.toInt().absoluteValue
    var text by rememberSaveable(value) {
        mutableStateOf(
            value.let {
                var string = ""
                it.forEachIndexed { index, float ->
                    string += "$float, "
                    if (index % rows == (rows - 1)) string += "\n"
                }
                string.dropLast(3)
            }
        )
    }
    RoundedTextField(
        enabled = !previewOnly,
        modifier = Modifier.padding(16.dp),
        singleLine = false,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { text = it },
        onLoseFocusTransformation = {
            val matrix = filter.newInstance().value as FloatArray
            this.trim { it.isWhitespace() }.split(",").mapIndexed { index, num ->
                num.toFloatOrNull()?.let {
                    matrix[index] = it
                }
            }
            onFilterChange(matrix)
            this
        },
        endIcon = {
            EnhancedIconButton(
                onClick = {
                    val matrix = filter.newInstance().value as FloatArray
                    text.trim { it.isWhitespace() }.split(",")
                        .mapIndexed { index, num ->
                            num.toFloatOrNull()?.let {
                                matrix[index] = it
                            }
                        }
                    onFilterChange(matrix)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Done,
                    contentDescription = "Done"
                )
            }
        },
        value = text,
        label = {
            Text(stringResource(R.string.float_array_of))
        }
    )
}