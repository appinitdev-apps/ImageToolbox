/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.enhanced

import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.t8rin.imagetoolbox.core.settings.domain.model.FlingType
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import io.iamjosephmj.flinger.adaptive.AdaptiveMode
import io.iamjosephmj.flinger.behaviours.FlingPresets

@Composable
fun enhancedFlingBehavior(
    flingType: FlingType = LocalSettingsState.current.flingType
): FlingBehavior = when (flingType) {
    FlingType.DEFAULT -> FlingPresets.androidNative()
    FlingType.SMOOTH -> FlingPresets.smooth()
    FlingType.IOS_STYLE -> FlingPresets.iOSStyle()
    FlingType.SMOOTH_CURVE -> FlingPresets.smoothCurve()
    FlingType.QUICK_STOP -> FlingPresets.quickStop()
    FlingType.BOUNCY -> FlingPresets.bouncy()
    FlingType.FLOATY -> FlingPresets.floaty()
    FlingType.SNAPPY -> FlingPresets.snappy()
    FlingType.ULTRA_SMOOTH -> FlingPresets.ultraSmooth()
    FlingType.ADAPTIVE -> FlingPresets.adaptive(AdaptiveMode.Precision)
    FlingType.ACCESSIBILITY_AWARE -> FlingPresets.accessibilityAware()
    FlingType.REDUCED_MOTION -> FlingPresets.reducedMotion()
}

fun Modifier.enhancedVerticalScroll(
    state: ScrollState,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
) = this.composed {
    Modifier.verticalScroll(
        state = state,
        enabled = enabled,
        flingBehavior = flingBehavior ?: enhancedFlingBehavior(),
        reverseScrolling = reverseScrolling,
    )
}

fun Modifier.enhancedVerticalScroll(
    state: ScrollState,
    overscrollEffect: OverscrollEffect?,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
) = this.composed {
    Modifier.verticalScroll(
        state = state,
        enabled = enabled,
        flingBehavior = flingBehavior ?: enhancedFlingBehavior(),
        reverseScrolling = reverseScrolling,
        overscrollEffect = overscrollEffect
    )
}

fun Modifier.enhancedHorizontalScroll(
    state: ScrollState,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
) = this.composed {
    Modifier.horizontalScroll(
        state = state,
        enabled = enabled,
        flingBehavior = flingBehavior ?: enhancedFlingBehavior(),
        reverseScrolling = reverseScrolling,
    )
}

fun Modifier.enhancedHorizontalScroll(
    state: ScrollState,
    overscrollEffect: OverscrollEffect?,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
) = this.composed {
    Modifier.horizontalScroll(
        state = state,
        enabled = enabled,
        flingBehavior = flingBehavior ?: enhancedFlingBehavior(),
        reverseScrolling = reverseScrolling,
        overscrollEffect = overscrollEffect
    )
}