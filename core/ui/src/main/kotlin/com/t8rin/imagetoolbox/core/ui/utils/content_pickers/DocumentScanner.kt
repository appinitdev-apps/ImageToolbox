/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.content_pickers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.t8rin.imagetoolbox.core.ui.utils.helper.ScanResult

@Stable
@Immutable
interface DocumentScanner : Scanner

@Composable
fun rememberDocumentScanner(
    onSuccess: (ScanResult) -> Unit
): DocumentScanner = rememberDocumentScannerImpl(onSuccess)