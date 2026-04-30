/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.crash.data

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics.Event
import com.google.firebase.analytics.FirebaseAnalytics.Param
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.crashlytics.crashlytics
import com.t8rin.imagetoolbox.core.domain.remote.AnalyticsManager
import com.t8rin.imagetoolbox.core.ui.utils.helper.DeviceInfo.Companion.get

internal object AnalyticsManagerImpl : AnalyticsManager {

    override var allowCollectCrashlytics: Boolean = false

    override var allowCollectAnalytics: Boolean = false

    override fun updateAnalyticsCollectionEnabled(value: Boolean) {
        analytics.setAnalyticsCollectionEnabled(value)
        allowCollectAnalytics = value
    }

    override fun updateAllowCollectCrashlytics(value: Boolean) {
        crashlytics.isCrashlyticsCollectionEnabled = value
        allowCollectCrashlytics = value

        if (value) {
            crashlytics.sendUnsentReports()
        }
    }

    override fun sendReport(throwable: Throwable) {
        if (allowCollectCrashlytics) {
            crashlytics.apply {
                recordException(throwable)
                sendUnsentReports()
            }
        }
    }

    override fun registerScreenOpen(screenName: String) {
        if (allowCollectAnalytics) {
            analytics.apply {
                logEvent(Event.SELECT_CONTENT) { param(Param.CONTENT_TYPE, screenName) }
                logEvent(screenName) { param(Param.CONTENT, deviceInfo()) }
            }
        }
    }

    private fun deviceInfo(): String {
        val info = get()

        return listOf(
            "Device: ${info.device}",
            "App Version: ${info.appVersion}"
        ).joinToString(",")
    }

    private val analytics get() = Firebase.analytics
    private val crashlytics get() = Firebase.crashlytics
}