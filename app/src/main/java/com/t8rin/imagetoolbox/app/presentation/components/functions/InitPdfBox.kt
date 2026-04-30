/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import android.app.Application
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader

internal fun Application.initPdfBox() = PDFBoxResourceLoader.init(this)