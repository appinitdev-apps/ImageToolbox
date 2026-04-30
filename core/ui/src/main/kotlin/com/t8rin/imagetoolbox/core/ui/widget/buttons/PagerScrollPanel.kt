/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t8rin.imagetoolbox.core.resources.icons.ArrowBackIos
import com.t8rin.imagetoolbox.core.resources.icons.ArrowForwardIos
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import kotlinx.coroutines.launch

@Composable
fun PagerScrollPanel(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier.container(
            shape = ShapeDefaults.circle,
            resultPadding = 4.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        EnhancedIconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        (pagerState.currentPage - 1).takeIf { it >= 0 }
                            ?: (pagerState.pageCount - 1)
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "${pagerState.currentPage + 1} / ${pagerState.pageCount}",
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )

        EnhancedIconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        (pagerState.currentPage + 1) % pagerState.pageCount
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}