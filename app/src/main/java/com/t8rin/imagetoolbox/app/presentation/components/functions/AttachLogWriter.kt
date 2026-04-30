/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import com.t8rin.imagetoolbox.app.presentation.components.ImageToolboxApplication
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.ui.utils.helper.DeviceInfo
import com.t8rin.imagetoolbox.core.utils.Logger
import com.t8rin.imagetoolbox.core.utils.attachLogWriter


internal fun ImageToolboxApplication.attachLogWriter() {
    Logger.attachLogWriter(
        context = this@attachLogWriter,
        fileProvider = getString(R.string.file_provider),
        logsFilename = "image_toolbox_logs.txt",
        startupLog = Logger.Log(
            tag = "Device Info",
            message = "--${DeviceInfo.get()}--",
            level = Logger.Level.Info
        ),
        errorHandler = analyticsManager::sendReport,
        isSyncCreate = false
    )
}