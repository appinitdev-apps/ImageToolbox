/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils

import android.app.Application

abstract class ComposeApplication : Application() {
    abstract fun runSetup()

    companion object {
        fun wrap(application: Application): ComposeApplication? = application as? ComposeApplication
    }
}