/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.saving

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.documentfile.provider.DocumentFile
import com.t8rin.imagetoolbox.core.data.saving.io.StreamWriteable
import com.t8rin.imagetoolbox.core.domain.saving.model.SaveTarget
import kotlinx.coroutines.coroutineScope
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@ConsistentCopyVisibility
internal data class SavingFolder private constructor(
    val outputStream: OutputStream,
    val fileUri: Uri
) : StreamWriteable by StreamWriteable(outputStream) {
    companion object {
        suspend fun getInstance(
            context: Context,
            treeUri: Uri?,
            saveTarget: SaveTarget
        ): SavingFolder? = coroutineScope {
            if (treeUri == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val type = saveTarget.mimeType.entry
                    val path = "${Environment.DIRECTORY_DOCUMENTS}/AppInitDev"
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, saveTarget.filename)
                        put(
                            MediaStore.MediaColumns.MIME_TYPE,
                            type
                        )
                        put(
                            MediaStore.MediaColumns.RELATIVE_PATH,
                            path
                        )
                    }
                    val imageUri = context.contentResolver.insert(
                        MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY),
                        contentValues
                    ) ?: return@coroutineScope null

                    SavingFolder(
                        outputStream = context.contentResolver.openOutputStream(imageUri)
                            ?: return@coroutineScope null,
                        fileUri = imageUri
                    )
                } else {
                    val imagesDir = File(
                        Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS
                        ), "AppInitDev"
                    )
                    if (!imagesDir.exists()) imagesDir.mkdir()

                    val filename = saveTarget.filename ?: return@coroutineScope null

                    SavingFolder(
                        outputStream = FileOutputStream(File(imagesDir, filename)),
                        fileUri = File(imagesDir, filename).toUri()
                    )
                }
            } else if (DocumentFile.isDocumentUri(context, treeUri)) {
                SavingFolder(
                    outputStream = context.contentResolver.openOutputStream(treeUri)
                        ?: return@coroutineScope null,
                    fileUri = treeUri
                )
            } else {
                val documentFile = DocumentFile.fromTreeUri(context, treeUri)

                if (documentFile == null || !documentFile.exists()) return@coroutineScope null

                val filename = saveTarget.filename ?: return@coroutineScope null

                val file = documentFile.createFile(saveTarget.mimeType.entry, filename)

                val imageUri = file?.uri ?: return@coroutineScope null

                SavingFolder(
                    outputStream = context.contentResolver.openOutputStream(imageUri)
                        ?: return@coroutineScope null,
                    fileUri = imageUri
                )
            }
        }
    }
}