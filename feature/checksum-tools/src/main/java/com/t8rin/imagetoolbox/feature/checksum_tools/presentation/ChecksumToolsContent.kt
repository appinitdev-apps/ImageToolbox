/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.checksum_tools.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.domain.model.HashingType
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.HashTag
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.AdaptiveLayoutScreen
import com.t8rin.imagetoolbox.core.ui.widget.controls.selection.DataSelector
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedBadge
import com.t8rin.imagetoolbox.core.ui.widget.modifier.scaleOnTap
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumPage
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.ChecksumToolsTabs
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.pages.CalculateFromTextPage
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.pages.CalculateFromUriPage
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.pages.CompareWithUriPage
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.components.pages.CompareWithUrisPage
import com.t8rin.imagetoolbox.feature.checksum_tools.presentation.screenLogic.ChecksumToolsComponent

@Composable
fun ChecksumToolsContent(
    component: ChecksumToolsComponent
) {
    val pagerState = rememberPagerState { ChecksumPage.ENTRIES_COUNT }

    AdaptiveLayoutScreen(
        shouldDisableBackHandler = true,
        onGoBack = component.onGoBack,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.marquee()
            ) {
                Text(
                    text = stringResource(R.string.checksum_tools)
                )
                EnhancedBadge(
                    content = {
                        Text(
                            text = HashingType.entries.size.toString()
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .padding(bottom = 12.dp)
                        .scaleOnTap {
                            AppToastHost.showConfetti()
                        }
                )
            }
        },
        actions = {},
        topAppBarPersistentActions = {
            TopAppBarEmoji()
        },
        imagePreview = {},
        placeImagePreview = false,
        addHorizontalCutoutPaddingIfNoPreview = false,
        showImagePreviewAsStickyHeader = false,
        canShowScreenData = true,
        underTopAppBarContent = {
            ChecksumToolsTabs(pagerState)
        },
        contentPadding = 0.dp,
        controls = {
            val insets = WindowInsets.navigationBars.union(
                WindowInsets.displayCutout
            ).only(
                WindowInsetsSides.Horizontal
            ).asPaddingValues()

            DataSelector(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp)
                    .padding(insets),
                value = component.hashingType,
                containerColor = Color.Unspecified,
                selectedItemColor = MaterialTheme.colorScheme.secondary,
                onValueChange = component::updateChecksumType,
                entries = HashingType.entries,
                title = stringResource(R.string.algorithms),
                titleIcon = Icons.Rounded.HashTag,
                itemContentText = {
                    it.name
                }
            )
            val direction = LocalLayoutDirection.current
            HorizontalPager(
                state = pagerState,
                beyondViewportPageCount = 3,
                contentPadding = insets,
                pageSpacing = remember(insets, direction) {
                    20.dp + insets.calculateStartPadding(direction) + insets.calculateEndPadding(
                        direction
                    )
                },
                verticalAlignment = Alignment.Top
            ) { pageIndex ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (pageIndex) {
                        ChecksumPage.CalculateFromUri.INDEX -> {
                            CalculateFromUriPage(
                                component = component
                            )
                        }

                        ChecksumPage.CalculateFromText.INDEX -> {
                            CalculateFromTextPage(
                                component = component
                            )
                        }

                        ChecksumPage.CompareWithUri.INDEX -> {
                            CompareWithUriPage(
                                component = component
                            )
                        }

                        ChecksumPage.CompareWithUris.INDEX -> {
                            CompareWithUrisPage(
                                component = component
                            )
                        }
                    }
                }
            }
        },
        buttons = {}
    )
}