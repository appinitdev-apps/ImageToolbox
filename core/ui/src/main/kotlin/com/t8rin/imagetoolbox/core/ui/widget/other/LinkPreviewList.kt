/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.other

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.KeyboardArrowDown
import com.t8rin.imagetoolbox.core.resources.icons.Link
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.LinkPreview
import com.t8rin.imagetoolbox.core.ui.utils.helper.LinkUtils
import com.t8rin.imagetoolbox.core.ui.utils.helper.fetchLinkPreview
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container
import com.t8rin.imagetoolbox.core.ui.widget.text.TitleItem
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LinkPreviewList(
    text: String,
    externalLinks: List<String>? = null,
    modifier: Modifier
) {
    val settingsState = LocalSettingsState.current
    if (!settingsState.isLinkPreviewEnabled) return

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }
    var linkPreviewList by remember {
        mutableStateOf(emptyList<LinkPreview>())
    }
    var expanded by rememberSaveable { mutableStateOf(true) }
    val rotation by animateFloatAsState(if (expanded) 180f else 0f)

    LaunchedEffect(text, externalLinks) {
        if (linkPreviewList.isNotEmpty() && text.isNotEmpty()) delay(500)

        isLoading = true

        val links = LinkUtils.parseLinks(text) + externalLinks.orEmpty()

        linkPreviewList = links.map(LinkPreview::empty)

        launch {
            linkPreviewList = links.map { link ->
                async {
                    fetchLinkPreview(link)
                }
            }.awaitAll()
        }

        isLoading = false
    }

    val links = remember(expanded, linkPreviewList) {
        if (linkPreviewList.size > 3 && expanded) {
            linkPreviewList
        } else linkPreviewList.take(3)
    }

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(),
        visible = !isLoading && linkPreviewList.isNotEmpty()
    ) {
        Column(
            modifier = Modifier
                .then(modifier)
                .container(
                    shape = ShapeDefaults.large,
                    resultPadding = 0.dp
                )
                .padding(8.dp)
        ) {
            Column {
                TitleItem(
                    text = stringResource(R.string.links),
                    icon = Icons.Rounded.Link,
                    modifier = Modifier.padding(8.dp),
                    endContent = {
                        AnimatedVisibility(
                            visible = linkPreviewList.size > 3,
                            modifier = Modifier.size(32.dp)
                        ) {
                            EnhancedIconButton(
                                onClick = { expanded = !expanded }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.KeyboardArrowDown,
                                    contentDescription = "Expand",
                                    modifier = Modifier.rotate(rotation)
                                )
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    links.forEachIndexed { index, link ->
                        LinkPreviewCard(
                            linkPreview = link,
                            shape = ShapeDefaults.byIndex(
                                index = index,
                                size = links.size
                            )
                        )
                    }
                }
            }
        }
    }
}