/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.libraries_info.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.plus
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.ui.compose.m3.chipColors
import com.mikepenz.aboutlibraries.ui.compose.m3.libraryColors
import com.mikepenz.aboutlibraries.ui.compose.util.htmlReadyLicenseContent
import com.mikepenz.aboutlibraries.util.withContext
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.ArrowBack
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedIconButton
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedTopAppBar
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.EnhancedTopAppBarType
import com.t8rin.imagetoolbox.core.ui.widget.other.TopAppBarEmoji
import com.t8rin.imagetoolbox.core.ui.widget.text.marquee
import com.t8rin.imagetoolbox.feature.libraries_info.presentation.components.LibrariesContainer
import com.t8rin.imagetoolbox.feature.libraries_info.presentation.components.link
import com.t8rin.imagetoolbox.feature.libraries_info.presentation.screenLogic.LibrariesInfoComponent
import kotlinx.collections.immutable.toPersistentList


@Composable
fun LibrariesInfoContent(
    component: LibrariesInfoComponent
) {
    val context = LocalContext.current

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            EnhancedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.open_source_licenses),
                        modifier = Modifier.marquee()
                    )
                },
                navigationIcon = {
                    EnhancedIconButton(
                        onClick = component.onGoBack
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    TopAppBarEmoji()
                },
                type = EnhancedTopAppBarType.Large,
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->
        val linkHandler = LocalUriHandler.current

        val libraries = remember(context) {
            Libs.Builder()
                .withContext(context)
                .build().let { libs ->
                    libs.copy(
                        libraries = libs.libraries.distinctBy {
                            it.name
                        }.filter { it.licenses.isNotEmpty() }.sortedWith(
                            compareBy(
                                { !it.name.contains("T8RIN", true) },
                                { it.name }
                            ),
                        ).toPersistentList()
                    )
                }
        }

        LibrariesContainer(
            libraries = libraries,
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding + PaddingValues(12.dp),
            dimensions = LibraryDefaults.libraryDimensions(
                itemSpacing = 4.dp
            ),
            colors = LibraryDefaults.libraryColors(
                versionChipColors = LibraryDefaults.chipColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(0.5f),
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ),
            onLibraryClick = { library ->
                val license = library.licenses.firstOrNull()
                val url = library.link()

                if (!license?.htmlReadyLicenseContent.isNullOrBlank()) {
                    component.selectLibrary(library)
                } else if (!url.isNullOrBlank()) {
                    linkHandler.openUri(url)
                }
            }
        )
    }
}