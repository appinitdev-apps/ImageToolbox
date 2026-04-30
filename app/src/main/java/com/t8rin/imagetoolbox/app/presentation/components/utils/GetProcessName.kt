/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context.ACTIVITY_SERVICE
import android.os.Build.VERSION.SDK_INT
import com.t8rin.imagetoolbox.core.utils.makeLog

internal fun Application.isMain(): Boolean =
    getProcessName().makeLog("Current Process") == packageName.makeLog("Current packageName")


@SuppressLint("PrivateApi")
internal fun Application.getProcessName(): String? {
    if (SDK_INT >= 28) {
        return Application.getProcessName()
    }

    // Try using ActivityThread to determine the current process name.
    try {
        val activityThread = Class.forName(
            "android.app.ActivityThread",
            false,
            this::class.java.classLoader
        )
        val packageName: Any?
        val currentProcessName = activityThread.getDeclaredMethod("currentProcessName")
        currentProcessName.isAccessible = true
        packageName = currentProcessName.invoke(null)
        if (packageName is String) {
            return packageName
        }
    } catch (exception: Throwable) {
        exception.makeLog()
    }

    // Fallback to the most expensive way
    val pid: Int = android.os.Process.myPid()
    val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager

    val processes = am.runningAppProcesses
    if (processes != null && processes.isNotEmpty()) {
        for (process in processes) {
            if (process.pid == pid) {
                return process.processName
            }
        }
    }

    return null
}