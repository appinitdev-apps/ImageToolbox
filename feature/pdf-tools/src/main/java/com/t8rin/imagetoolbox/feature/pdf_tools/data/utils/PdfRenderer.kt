/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.pdf_tools.data.utils

import android.graphics.Bitmap
import android.os.Build
import android.os.ext.SdkExtensions
import androidx.annotation.ChecksSdkIntAtLeast
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.utils.makeLog
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.encryption.InvalidPasswordException
import com.tom_roush.pdfbox.rendering.PDFRenderer
import java.lang.AutoCloseable
import kotlin.math.roundToInt

class PdfRenderer(
    val pDocument: PDDocument
) : PDFRenderer(pDocument), AutoCloseable {
    val pageCount: Int get() = pDocument.numberOfPages
    val pageIndices: List<Int> get() = pDocument.pageIndices

    fun openPage(index: Int): Page = pDocument.getPage(index).let { page ->
        page.cropBox.run {
            Page(
                width = width.roundToInt(),
                height = height.roundToInt()
            )
        }
    }

    fun safeRenderDpi(
        pageIndex: Int,
        dpi: Float
    ): Bitmap = try {
        System.gc()
        if (openPage(pageIndex).run { width * height * 4 <= 4096 * 4096 * 4 }) {
            renderImageWithDPI(pageIndex, dpi)
        } else {
            renderImage(pageIndex)
        }
    } catch (t1: Throwable) {
        t1.makeLog("safeRenderDpi")
        System.gc()
        try {
            renderImage(pageIndex)
        } catch (t2: Throwable) {
            t2.makeLog("safeRenderDpi")
            System.gc()
            renderImage(pageIndex, 0.5f)
        }
    } finally {
        System.gc()
    }

    override fun close() = pDocument.close()

    class Page(
        val width: Int,
        val height: Int
    ) {
        val size = IntegerSize(width, height)
    }
}

fun PdfRenderer(
    uri: String,
    password: String? = null,
    onFailure: (Throwable) -> Unit = {},
    onPasswordRequest: (() -> Unit)? = null
): PdfRenderer? = runCatching {
    safeOpenPdf(
        uri = uri,
        password = password
    ).let(::PdfRenderer)
}.onFailure { throwable ->
    when (throwable) {
        is InvalidPasswordException -> onPasswordRequest?.invoke() ?: onFailure(throwable)
        else -> onFailure(throwable)
    }
}.getOrNull()


@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
fun canUseNewPdf(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

@ChecksSdkIntAtLeast(api = 13, extension = Build.VERSION_CODES.S)
fun canUseNewPdfFully(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM
        || Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
        && SdkExtensions.getExtensionVersion(Build.VERSION_CODES.S) >= 13