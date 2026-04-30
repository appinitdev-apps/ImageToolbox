/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.image.utils

import android.content.Context
import android.graphics.Bitmap
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.AvifBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.BmpBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.HeicBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.IcoBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.Jpeg2000Backend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.JpegliBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.JpgBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.JxlBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.MozJpegBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.OxiPngBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.PngLosslessBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.PngLossyBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.QoiBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.StaticGifBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.TiffBackend
import com.t8rin.imagetoolbox.core.data.image.utils.compressor.WebpBackend
import com.t8rin.imagetoolbox.core.domain.image.ImageScaler
import com.t8rin.imagetoolbox.core.domain.image.model.ImageFormat
import com.t8rin.imagetoolbox.core.domain.image.model.Quality
import com.t8rin.imagetoolbox.core.domain.image.model.isLossless


internal interface ImageCompressorBackend {

    suspend fun compress(
        image: Bitmap,
        quality: Quality
    ): ByteArray

    class Factory {
        fun create(
            imageFormat: ImageFormat,
            context: Context,
            imageScaler: ImageScaler<Bitmap>
        ): ImageCompressorBackend = when (imageFormat) {
            ImageFormat.Jpeg,
            ImageFormat.Jpg -> JpgBackend

            ImageFormat.Jpegli -> JpegliBackend
            ImageFormat.MozJpeg -> MozJpegBackend

            ImageFormat.Png.Lossless -> PngLosslessBackend
            ImageFormat.Png.Lossy -> PngLossyBackend
            ImageFormat.Png.OxiPNG -> OxiPngBackend

            ImageFormat.Webp.Lossless,
            ImageFormat.Webp.Lossy -> WebpBackend(isLossless = imageFormat.isLossless)

            ImageFormat.Jxl.Lossless,
            ImageFormat.Jxl.Lossy -> JxlBackend(isLossless = imageFormat.isLossless)

            ImageFormat.Tif,
            ImageFormat.Tiff -> TiffBackend(context)

            ImageFormat.Heic.Lossless,
            ImageFormat.Heif.Lossless,
            ImageFormat.Heic.Lossy,
            ImageFormat.Heif.Lossy -> HeicBackend(isLossless = imageFormat.isLossless)

            ImageFormat.Avif.Lossless,
            ImageFormat.Avif.Lossy -> AvifBackend(isLossless = imageFormat.isLossless)

            ImageFormat.Jpeg2000.J2k -> Jpeg2000Backend(isJ2K = true)
            ImageFormat.Jpeg2000.Jp2 -> Jpeg2000Backend(isJ2K = false)

            ImageFormat.Gif -> StaticGifBackend
            ImageFormat.Bmp -> BmpBackend
            ImageFormat.Qoi -> QoiBackend
            ImageFormat.Ico -> IcoBackend(imageScaler)
        }
    }

}