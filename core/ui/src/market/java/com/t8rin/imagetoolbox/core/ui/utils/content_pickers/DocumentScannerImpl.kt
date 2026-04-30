/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.content_pickers

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.utils.helper.ScanResult
import com.t8rin.imagetoolbox.core.ui.utils.provider.LocalComponentActivity

private class DocumentScannerImpl(
    private val context: ComponentActivity,
    private val scannerLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>,
    private val onFailure: (Throwable) -> Unit
) : DocumentScanner {

    override fun scan() {
        val options = GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(true)
            .setResultFormats(
                GmsDocumentScannerOptions.RESULT_FORMAT_JPEG,
                GmsDocumentScannerOptions.RESULT_FORMAT_PDF
            )
            .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
            .build()

        val scanner = GmsDocumentScanning.getClient(options)

        scanner.getStartScanIntent(context)
            .addOnSuccessListener { intentSender ->
                scannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
            }
            .addOnFailureListener(onFailure)
    }

}

@Composable
internal fun rememberDocumentScannerImpl(
    onSuccess: (ScanResult) -> Unit
): DocumentScanner {
    val context = LocalComponentActivity.current

    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            runCatching {
                GmsDocumentScanningResult.fromActivityResultIntent(result.data)?.apply {
                    onSuccess(
                        ScanResult(
                            imageUris = pages?.let { pages ->
                                pages.map { it.imageUri }
                            } ?: emptyList(),
                            pdfUri = pdf?.uri
                        )
                    )
                }
            }.onFailure(AppToastHost::showFailureToast)
        }
    }

    return remember(context, scannerLauncher) {
        DocumentScannerImpl(
            context = context,
            scannerLauncher = scannerLauncher,
            onFailure = AppToastHost::showFailureToast
        )
    }
}