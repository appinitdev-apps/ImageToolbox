/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.crash.presentation.components

import android.content.Context
import android.content.Intent
import com.t8rin.imagetoolbox.core.crash.di.CrashModule
import com.t8rin.imagetoolbox.core.crash.presentation.CrashActivity
import com.t8rin.imagetoolbox.core.domain.remote.AnalyticsManager
import com.t8rin.imagetoolbox.core.utils.makeLog
import kotlin.system.exitProcess

private class GlobalExceptionHandler<T : CrashHandler> private constructor(
    private val applicationContext: Context,
    private val defaultHandler: Thread.UncaughtExceptionHandler?,
    private val activityToBeLaunched: Class<T>
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(
        p0: Thread,
        p1: Throwable
    ) {
        //sendReport(p1)

        runCatching {
            p1.makeLog("FATAL_EXCEPTION")

            applicationContext.launchActivity(activityToBeLaunched, p1)
            exitProcess(0)
        }.getOrElse {
            defaultHandler?.uncaughtException(p0, p1)
        }
    }

    private fun Context.launchActivity(
        activity: Class<*>,
        throwable: Throwable
    ) = applicationContext.startActivity(
        Intent(applicationContext, activity).putExtra(
            CrashHandler.EXCEPTION_EXTRA,
            CrashHandler.getCrashInfoAsExtra(throwable)
        ).addFlags(defFlags)
    )

    companion object : AnalyticsManager by CrashModule.analyticsManager() {

        fun <T : CrashHandler> initialize(
            applicationContext: Context,
            activityToBeLaunched: Class<T>,
        ) = Thread.setDefaultUncaughtExceptionHandler(
            GlobalExceptionHandler(
                applicationContext = applicationContext,
                defaultHandler = Thread.getDefaultUncaughtExceptionHandler()!!,
                activityToBeLaunched = activityToBeLaunched
            )
        )

    }
}

private const val defFlags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
        Intent.FLAG_ACTIVITY_NEW_TASK or
        Intent.FLAG_ACTIVITY_CLEAR_TASK

fun Context.applyGlobalExceptionHandler() = GlobalExceptionHandler.initialize(
    applicationContext = applicationContext,
    activityToBeLaunched = CrashActivity::class.java,
)