/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.enhanced.derivative

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedSliderItem
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import kotlinx.collections.immutable.toPersistentMap
import kotlin.math.roundToInt

@Composable
fun OnlyAllowedSliderItem(
    label: String,
    icon: ImageVector,
    value: Int,
    allowed: Collection<Int>,
    maxAllowed: Int = Int.MAX_VALUE,
    onValueChange: (Int) -> Unit,
    valueSuffix: String = " px",
    shape: Shape = ShapeDefaults.large,
) {
    val availableAllowed = allowed.filter { it < maxAllowed }
    val effectiveAllowed = availableAllowed.ifEmpty { listOf(allowed.first()) }
    val clampedValue = value.coerceAtMost(effectiveAllowed.last())
    var index by remember(clampedValue, effectiveAllowed) {
        mutableIntStateOf(effectiveAllowed.indexOf(clampedValue).coerceAtLeast(0))
    }
    LaunchedEffect(maxAllowed) {
        if (value >= maxAllowed && effectiveAllowed.isNotEmpty()) {
            onValueChange(effectiveAllowed.last())
        }
    }

    EnhancedSliderItem(
        value = index,
        internalStateTransformation = { it.roundToInt() },
        onValueChange = {
            val newIdx = it.roundToInt().coerceIn(effectiveAllowed.indices)
            if (newIdx != index) {
                index = newIdx
                onValueChange(effectiveAllowed[newIdx])
            }
        },
        valueRange = 0f..(effectiveAllowed.lastIndex.toFloat().coerceAtLeast(0f)),
        steps = (effectiveAllowed.size - 2).coerceAtLeast(0),
        enabled = effectiveAllowed.size > 1,
        title = label,
        valuesPreviewMapping = remember(effectiveAllowed) {
            buildMap {
                effectiveAllowed.forEachIndexed { index, value ->
                    put(index.toFloat(), "${value}${valueSuffix}")
                }
            }.toPersistentMap()
        },
        icon = icon,
        isAnimated = false,
        canInputValue = false,
        shape = shape,
    )
}