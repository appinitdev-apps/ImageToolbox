/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.page

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.text.RoundedTextField

@Composable
internal fun PageInputField(
    selectedPages: List<Int>,
    onPagesChanged: (List<Int>) -> Unit
) {
    var text by remember {
        mutableStateOf(PagesSelectionParser.formatPageOutput(selectedPages))
    }

    RoundedTextField(
        value = text,
        onValueChange = {
            text = it
            val parsedPages = PagesSelectionParser.parsePageInput(it)
            onPagesChanged(parsedPages)
        },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Start
        ),
        label = stringResource(R.string.custom_pages),
        modifier = Modifier
            .container(
                shape = MaterialTheme.shapes.large,
                resultPadding = 8.dp
            ),
        singleLine = false
    )
}
