/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import android.os.Build.BRAND
import android.os.Build.DEVICE
import android.os.Build.MODEL
import android.os.Build.VERSION.RELEASE
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatDelegate
import com.t8rin.imagetoolbox.core.resources.BuildConfig.FLAVOR
import com.t8rin.imagetoolbox.core.resources.BuildConfig.VERSION_CODE
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.getDisplayName
import com.t8rin.imagetoolbox.core.utils.makeLog

@ConsistentCopyVisibility
data class DeviceInfo private constructor(
    val device: String,
    val sdk: String,
    val appVersion: String,
    val flavor: String,
    val locale: String
) {
    companion object {
        fun get(): DeviceInfo {
            val device = "$MODEL ($BRAND - $DEVICE)"

            val sdk = "$SDK_INT (Android $RELEASE)"

            val appVersion = "$AppVersion ($VERSION_CODE)"

            val flavor = FLAVOR

            val locale = AppCompatDelegate.getApplicationLocales().getDisplayName()

            return DeviceInfo(
                device = device,
                sdk = sdk,
                appVersion = appVersion,
                flavor = flavor,
                locale = locale
            )
        }

        fun getAsString(): String {
            val (device, sdk, appVersion, flavor, locale) = get()

            return listOf(
                "Device: $device",
                "SDK: $sdk",
                "App Version: $appVersion",
                "Flavor: $flavor",
                "Locale: $locale"
            ).joinToString("\n")
        }

        fun pushLog(extra: String? = null) {
            getAsString().makeLog("DeviceInfo".plus(extra?.let { " $it" }.orEmpty()))
        }

        fun isPixel() = getAsString().contains(
            other = "google",
            ignoreCase = true
        )
    }
}