/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.crash.presentation.components

import android.content.Intent
import android.util.Log
import com.t8rin.imagetoolbox.core.domain.ISSUE_TRACKER
import com.t8rin.imagetoolbox.core.ui.utils.helper.DeviceInfo
import com.t8rin.imagetoolbox.core.utils.encodeEscaped

interface CrashHandler {

    fun getIntent(): Intent

    private val intent: Intent
        @JvmName("getIntentValue")
        get() = getIntent()

    private fun getCrashReason(): String = intent.getStringExtra(EXCEPTION_EXTRA) ?: ""

    fun getCrashInfo(): CrashInfo {
        val crashReason = getCrashReason()
        val splitData = crashReason.split(DELIMITER)
        val exceptionName = splitData.first().trim()
        val stackTrace = splitData.drop(1).joinToString(DELIMITER)

        val title = "[Bug] App Crash: $exceptionName"

        val deviceInfo = DeviceInfo.getAsString()

        val body = listOf(deviceInfo, stackTrace).joinToString(DELIMITER)

        return CrashInfo(
            title = title,
            body = body,
            exceptionName = exceptionName,
            stackTrace = stackTrace
        )
    }

    companion object {
        internal const val EXCEPTION_EXTRA = "EXCEPTION_EXTRA"

        fun getCrashInfoAsExtra(
            throwable: Throwable
        ): String {
            val exceptionName = throwable::class.java.simpleName
            val stackTrace = Log.getStackTraceString(throwable)

            return listOf(exceptionName, stackTrace).joinToString(DELIMITER)
        }
    }
}

data class CrashInfo(
    val title: String,
    val body: String,
    val exceptionName: String,
    val stackTrace: String
) {
    val textToSend = listOf(title, body).joinToString(DELIMITER)

    val githubLink =
        "$ISSUE_TRACKER/new?title=${title.encodeEscaped()}&body=${body.encodeEscaped()}"
}

private const val DELIMITER = "\n\n"