/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.preferences

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults

private val DefaultStartTransition: AnimatedContentTransitionScope<ImageVector>.() -> ContentTransform =
    {
        fadeIn() + scaleIn() + slideInVertically() togetherWith fadeOut() + scaleOut() + slideOutVertically() using SizeTransform(
            clip = false
        )
    }

private val DefaultEndTransition: AnimatedContentTransitionScope<ImageVector>.() -> ContentTransform =
    {
        fadeIn() + scaleIn() togetherWith fadeOut() + scaleOut() using SizeTransform(clip = false)
    }


@Composable
fun PreferenceItem(
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    title: String,
    enabled: Boolean = true,
    subtitle: String? = null,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    autoShadowElevation: Dp = 1.dp,
    shape: Shape = ShapeDefaults.default,
    pressedShape: Shape = ShapeDefaults.pressed,
    containerColor: Color = Color.Unspecified,
    contentColor: Color = contentColorFor(backgroundColor = containerColor),
    overrideIconShapeContentColor: Boolean = false,
    drawStartIconContainer: Boolean = true,
    titleFontStyle: TextStyle = PreferenceItemDefaults.TitleFontStyle,
    startIconTransitionSpec: AnimatedContentTransitionScope<ImageVector>.() -> ContentTransform = DefaultStartTransition,
    endIconTransitionSpec: AnimatedContentTransitionScope<ImageVector>.() -> ContentTransform = DefaultEndTransition,
    onDisabledClick: (() -> Unit)? = null,
    placeBottomContentInside: Boolean = false,
    bottomContent: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
) {
    val targetIcon: (@Composable () -> Unit)? = if (startIcon == null) null else {
        {
            AnimatedContent(
                targetState = startIcon,
                transitionSpec = startIconTransitionSpec
            ) { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    }
    val targetEndIcon: (@Composable () -> Unit)? = if (endIcon == null) null else {
        {
            Box {
                AnimatedContent(
                    targetState = endIcon,
                    transitionSpec = endIconTransitionSpec
                ) { endIcon ->
                    Icon(
                        imageVector = endIcon,
                        contentDescription = null
                    )
                }
            }
        }
    }

    PreferenceItemOverload(
        autoShadowElevation = autoShadowElevation,
        contentColor = contentColor,
        onClick = onClick,
        onLongClick = onLongClick,
        enabled = enabled,
        title = title,
        subtitle = subtitle,
        startIcon = targetIcon,
        endIcon = targetEndIcon,
        overrideIconShapeContentColor = overrideIconShapeContentColor,
        shape = shape,
        pressedShape = pressedShape,
        containerColor = containerColor,
        modifier = modifier,
        titleFontStyle = titleFontStyle,
        onDisabledClick = onDisabledClick,
        drawStartIconContainer = drawStartIconContainer,
        placeBottomContentInside = placeBottomContentInside,
        bottomContent = bottomContent
    )
}


object PreferenceItemDefaults {

    val TitleFontStyle: TextStyle
        @Composable
        get() = LocalTextStyle.current.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp
        )

    val TitleFontStyleCentered: TextStyle
        @Composable
        get() = TitleFontStyle.copy(
            textAlign = TextAlign.Center
        )

    val TitleFontStyleCenteredSmall: TextStyle
        @Composable
        get() = TitleFontStyleSmall.copy(
            textAlign = TextAlign.Center
        )

    val TitleFontStyleSmall: TextStyle
        @Composable
        get() = LocalTextStyle.current.copy(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp,
            textAlign = TextAlign.Start
        )

}