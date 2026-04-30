/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.saving

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.t8rin.imagetoolbox.core.data.saving.KeepAliveForegroundService.Companion.ACTION_STOP
import com.t8rin.imagetoolbox.core.data.saving.KeepAliveForegroundService.Companion.ACTION_UPDATE
import com.t8rin.imagetoolbox.core.data.saving.KeepAliveForegroundService.Companion.EXTRA_DESC
import com.t8rin.imagetoolbox.core.data.saving.KeepAliveForegroundService.Companion.EXTRA_PROGRESS
import com.t8rin.imagetoolbox.core.data.saving.KeepAliveForegroundService.Companion.EXTRA_REMOVE_NOTIFICATION
import com.t8rin.imagetoolbox.core.data.saving.KeepAliveForegroundService.Companion.EXTRA_TITLE
import com.t8rin.imagetoolbox.core.domain.saving.FailureNotifier
import com.t8rin.imagetoolbox.core.domain.saving.KeepAliveService
import com.t8rin.imagetoolbox.core.domain.utils.tryAll
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class AndroidKeepAliveService @Inject constructor(
    @ApplicationContext private val context: Context,
    failureNotifier: FailureNotifier
) : KeepAliveService, FailureNotifier by failureNotifier {

    private val baseIntent: Intent
        get() = Intent(context, KeepAliveForegroundService::class.java)

    override fun updateOrStart(
        title: String,
        description: String,
        progress: Float
    ) {
        val intent = baseIntent
            .setAction(ACTION_UPDATE)
            .putExtra(EXTRA_TITLE, title)
            .putExtra(EXTRA_DESC, description)
            .putExtra(EXTRA_PROGRESS, progress)

        tryAll(
            { ContextCompat.startForegroundService(context, intent) },
            { context.startService(intent) }
        )
    }

    override fun stop(removeNotification: Boolean) {
        val intent = baseIntent
            .setAction(ACTION_STOP)
            .putExtra(EXTRA_REMOVE_NOTIFICATION, removeNotification)

        tryAll(
            { context.startService(intent) },
            { context.stopService(intent) }
        )
    }

}