/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.annotation.StringRes
import com.t8rin.imagetoolbox.core.resources.R

class AppContext private constructor(
    application: Context
) : ContextWrapper(application) {

    companion object {
        internal var appContext: AppContext? = null

        internal fun init(application: Context) {
            appContext = AppContext(application)
        }
    }

}

fun Context.initAppContext() = AppContext.init(this)

val appContext: AppContext
    get() = checkNotNull(AppContext.appContext) {
        "AppContext not initialized"
    }

fun getString(@StringRes resId: Int): String = appContext.getString(resId)

fun getString(
    @StringRes resId: Int,
    vararg formatArgs: Any?
): String = appContext.getString(resId, *formatArgs)

fun Throwable.extractMessage(): String = if (this is OutOfMemoryError) {
    getString(R.string.oom_description)
} else {
    getString(
        R.string.smth_went_wrong,
        (localizedMessage?.takeIf { it.isNotBlank() } ?: message)?.decodeEscaped().orEmpty()
            .ifEmpty {
                this::class.java.simpleName
            }
    )
}