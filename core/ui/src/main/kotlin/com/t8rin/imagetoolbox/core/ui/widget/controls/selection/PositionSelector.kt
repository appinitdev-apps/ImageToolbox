/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.selection

import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import com.t8rin.imagetoolbox.core.domain.model.Position
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Place
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults

@Composable
fun PositionSelector(
    value: Position,
    onValueChange: (Position) -> Unit,
    entries: List<Position> = Position.entries,
    modifier: Modifier = Modifier,
    shape: Shape = ShapeDefaults.large,
    color: Color = MaterialTheme.colorScheme.surface,
    selectedItemColor: Color = MaterialTheme.colorScheme.tertiary,
) {
    DataSelector(
        value = value,
        onValueChange = onValueChange,
        entries = entries,
        spanCount = 2,
        title = stringResource(R.string.position),
        titleIcon = Icons.Outlined.Place,
        itemContentText = { it.translatedName },
        modifier = modifier,
        shape = shape,
        containerColor = color,
        selectedItemColor = selectedItemColor
    )
}

private val Position.translatedName: String
    @Composable
    get() = when (this) {
        Position.Center -> stringResource(id = R.string.center)
        Position.TopLeft -> stringResource(id = R.string.top_left)
        Position.TopRight -> stringResource(id = R.string.top_right)
        Position.BottomLeft -> stringResource(id = R.string.bottom_left)
        Position.BottomRight -> stringResource(id = R.string.bottom_right)
        Position.TopCenter -> stringResource(id = R.string.top_center)
        Position.CenterRight -> stringResource(id = R.string.center_right)
        Position.BottomCenter -> stringResource(id = R.string.bottom_center)
        Position.CenterLeft -> stringResource(id = R.string.center_left)
    }