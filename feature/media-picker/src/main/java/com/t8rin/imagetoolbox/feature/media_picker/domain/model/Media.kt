/* #AppInitDev -> Photo Utility Hub */



@file:Suppress("unused")

package com.t8rin.imagetoolbox.feature.media_picker.domain.model

import androidx.core.net.toUri
import com.t8rin.imagetoolbox.core.domain.utils.humanFileSize
import com.t8rin.imagetoolbox.core.utils.fileSize


data class Media(
    val id: Long = 0,
    val label: String,
    val uri: String,
    val path: String,
    val relativePath: String,
    val albumID: Long,
    val albumLabel: String,
    val timestamp: Long,
    val expiryTimestamp: Long? = null,
    val takenTimestamp: Long? = null,
    val fullDate: String,
    val mimeType: String,
    val duration: String? = null,
) {
    val fileSize: Long by lazy { uri.toUri().fileSize() ?: 0 }

    val humanFileSize: String by lazy { humanFileSize(fileSize) }

    val isVideo: Boolean = mimeType.startsWith("video/") && duration != null

    val isImage: Boolean = mimeType.startsWith("image/")

    /**
     * Used to determine if the Media object is not accessible
     * via MediaStore.
     * This happens when the user tries to open media from an app
     * using external sources (in our case, Gallery Media Viewer), but
     * the specific media is only available internally in that app
     * (Android/data(OR media)/com.package.name/)
     *
     * If it's readUriOnly then we know that we should expect a barebone
     * Media object with limited functionality (no favorites, trash, timestamp etc)
     */
    val readUriOnly: Boolean = albumID == -99L && albumLabel == ""

    /**
     * Determine if the current media is a raw format
     *
     * Checks if [mimeType] starts with "image/x-" or "image/vnd."
     *
     * Most used formats:
     * - ARW: image/x-sony-arw
     * - CR2: image/x-canon-cr2
     * - CRW: image/x-canon-crw
     * - DCR: image/x-kodak-dcr
     * - DNG: image/x-adobe-dng
     * - ERF: image/x-epson-erf
     * - K25: image/x-kodak-k25
     * - KDC: image/x-kodak-kdc
     * - MRW: image/x-minolta-mrw
     * - NEF: image/x-nikon-nef
     * - ORF: image/x-olympus-orf
     * - PEF: image/x-pentax-pef
     * - RAF: image/x-fuji-raf
     * - RAW: image/x-panasonic-raw
     * - SR2: image/x-sony-sr2
     * - SRF: image/x-sony-srf
     * - X3F: image/x-sigma-x3f
     *
     * Other proprietary image types in the standard:
     * image/vnd.manufacturer.filename_extension for instance for NEF by Nikon and .mrv for Minolta:
     * - NEF: image/vnd.nikon.nef
     * - Minolta: image/vnd.minolta.mrw
     */
    val isRaw: Boolean =
        mimeType.isNotBlank() && (mimeType.startsWith("image/x-") || mimeType.startsWith("image/vnd."))

    val fileExtension: String = label.substringAfterLast(".").removePrefix(".")

    val volume: String = path.substringBeforeLast("/").removeSuffix(relativePath.removeSuffix("/"))
}

const val WEEKLY_DATE_FORMAT = "EEEE"
const val DEFAULT_DATE_FORMAT = "EEE, MMMM d"
const val EXTENDED_DATE_FORMAT = "EEE, MMM d, yyyy"
const val FULL_DATE_FORMAT = "EEEE, MMMM d, yyyy, hh:mm a"
const val HEADER_DATE_FORMAT = "MMMM d, yyyy\n" + "h:mm a"
const val EXIF_DATE_FORMAT = "MMMM d, yyyy • h:mm a"