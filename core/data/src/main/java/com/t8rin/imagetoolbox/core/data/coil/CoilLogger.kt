/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.coil

import coil3.util.DebugLogger
import coil3.util.Logger
import com.t8rin.imagetoolbox.core.utils.makeLog
import com.t8rin.imagetoolbox.core.utils.Logger as RealLogger

internal class CoilLogger : Logger {

    private val delegate = DebugLogger()

    override var minLevel: Logger.Level
        get() = delegate.minLevel
        set(value) {
            delegate.minLevel = value
        }

    override fun log(
        tag: String,
        level: Logger.Level,
        message: String?,
        throwable: Throwable?
    ) {
        message?.takeIf {
            "NullRequestData" !in it && "PDF" !in it
        }?.makeLog(tag, level.toLogger())

        throwable?.takeIf {
            "The request's data is null" !in it.message.orEmpty() && "PDF" !in it.message.orEmpty()
        }?.makeLog(tag)

        delegate.log(
            tag = tag,
            level = level,
            message = message,
            throwable = throwable
        )
    }

    private fun Logger.Level.toLogger(): RealLogger.Level = when (this) {
        Logger.Level.Verbose -> RealLogger.Level.Verbose
        Logger.Level.Debug -> RealLogger.Level.Debug
        Logger.Level.Info -> RealLogger.Level.Info
        Logger.Level.Warn -> RealLogger.Level.Warn
        Logger.Level.Error -> RealLogger.Level.Error
    }

}