/* #AppInitDev -> Photo Utility Hub */



@file:SuppressLint("StringFormatMatches")

package com.t8rin.imagetoolbox.core.ui.utils.helper

import android.annotation.SuppressLint
import com.t8rin.imagetoolbox.core.domain.saving.model.SaveResult
import com.t8rin.imagetoolbox.core.domain.utils.ListUtils.firstOfType
import com.t8rin.imagetoolbox.core.resources.Icons
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Info
import com.t8rin.imagetoolbox.core.resources.icons.Save
import com.t8rin.imagetoolbox.core.ui.widget.other.ToastDuration
import com.t8rin.imagetoolbox.core.utils.getString
import com.t8rin.imagetoolbox.core.utils.makeLog

interface SaveResultHandler {
    fun parseSaveResult(saveResult: SaveResult)

    fun parseFileSaveResult(saveResult: SaveResult)

    fun parseSaveResults(results: List<SaveResult>)
}

internal object SaveResultHandlerImpl : SaveResultHandler {
    override fun parseSaveResult(saveResult: SaveResult) {
        when (saveResult) {
            is SaveResult.Error.Exception -> {
                saveResult.throwable.makeLog("parseSaveResult")
                AppToastHost.showFailureToast(
                    throwable = saveResult.throwable
                )
            }

            is SaveResult.Skipped -> {
                AppToastHost.showToast(
                    message = getString(R.string.skipped_saving),
                    icon = Icons.Outlined.Info,
                    duration = ToastDuration.Short
                )
            }

            is SaveResult.Success -> {
                saveResult.message?.let {
                    AppToastHost.showToast(
                        message = it,
                        icon = Icons.Rounded.Save,
                        duration = ToastDuration.Long
                    )
                }
                AppToastHost.showConfetti()
                ReviewHandler.current.requestReview()
            }

            SaveResult.Error.MissingPermissions -> AppToastHost.showToast(AppToastHost.PERMISSION)
        }
    }

    override fun parseFileSaveResult(saveResult: SaveResult) {
        when (saveResult) {
            is SaveResult.Error.Exception -> {
                AppToastHost.showFailureToast(
                    throwable = saveResult.throwable
                )
            }

            is SaveResult.Skipped -> {
                AppToastHost.showToast(
                    message = getString(R.string.skipped_saving),
                    icon = Icons.Outlined.Info
                )
            }

            is SaveResult.Success -> {
                AppToastHost.showToast(
                    message = getString(R.string.saved_to_without_filename, ""),
                    icon = Icons.Rounded.Save
                )
                AppToastHost.showConfetti()
                ReviewHandler.current.requestReview()
            }

            SaveResult.Error.MissingPermissions -> AppToastHost.showToast(AppToastHost.PERMISSION)
        }
    }

    override fun parseSaveResults(results: List<SaveResult>) {
        if (results.size == 1) {
            return parseSaveResult(
                saveResult = results.first()
            )
        }

        if (results.any { it == SaveResult.Error.MissingPermissions }) {
            AppToastHost.showToast(AppToastHost.PERMISSION)
            return
        }

        val skipped = results.count { it is SaveResult.Skipped }
        val failed = results.count { it is SaveResult.Error }
        val done = results.count { it is SaveResult.Success }

        if (failed == 0 && done > 0) {
            if (done == 1) {
                val saveResult = results.firstOfType<SaveResult.Success>()
                val savingPath = saveResult?.savingPath ?: getString(R.string.default_folder)
                AppToastHost.showToast(
                    message = saveResult?.message ?: getString(
                        R.string.saved_to_without_filename,
                        savingPath
                    ),
                    icon = Icons.Rounded.Save,
                    duration = ToastDuration.Long
                )
            } else {
                val saveResult = results.firstOfType<SaveResult.Success>()

                if (saveResult?.isOverwritten == true) {
                    AppToastHost.showToast(
                        message = getString(R.string.images_overwritten),
                        icon = Icons.Rounded.Save,
                        duration = ToastDuration.Long
                    )
                } else {
                    val savingPath = saveResult?.savingPath ?: getString(R.string.default_folder)

                    AppToastHost.showToast(
                        message = getString(
                            R.string.saved_to_without_filename,
                            savingPath
                        ),
                        icon = Icons.Rounded.Save,
                        duration = ToastDuration.Long
                    )
                }
            }

            if (skipped > 0) {
                AppToastHost.showToast(
                    message = getString(R.string.skipped_saving_multiple, skipped),
                    icon = Icons.Outlined.Info,
                    duration = ToastDuration.Short
                )
            }

            AppToastHost.showConfetti()
            ReviewHandler.current.requestReview()
            return
        }

        if (failed > 0) {
            val saveResult = results.firstOfType<SaveResult.Success>()
            val errorSaveResult = results.firstOfType<SaveResult.Error>()

            if (done > 0) {
                AppToastHost.showToast(
                    message = saveResult?.message
                        ?: getString(
                            R.string.saved_to_without_filename,
                            saveResult?.savingPath
                        ),
                    icon = Icons.Rounded.Save,
                    duration = ToastDuration.Long
                )
            }
            AppToastHost.showFailureToast(getString(R.string.failed_to_save, failed))
            AppToastHost.showToast(
                message = getString(
                    R.string.smth_went_wrong,
                    errorSaveResult?.throwable?.localizedMessage ?: ""
                )
            )

            if (skipped > 0) {
                AppToastHost.showToast(
                    message = getString(R.string.skipped_saving_multiple, skipped),
                    icon = Icons.Outlined.Info,
                    duration = ToastDuration.Short
                )
            }
            return
        }

        if (skipped > 0 && done == 0 && failed == 0) {
            AppToastHost.showToast(
                message = getString(R.string.skipped_saving_multiple, skipped),
                icon = Icons.Outlined.Info,
                duration = ToastDuration.Short
            )
        }
    }
}