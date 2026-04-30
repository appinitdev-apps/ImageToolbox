/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory
import com.t8rin.imagetoolbox.core.utils.makeLog

internal object ReviewHandlerImpl : ReviewHandler() {

    override fun showReview(activity: Activity) {
        runCatching {
            ReviewManagerFactory.create(activity).let { reviewManager ->
                reviewManager
                    .requestReviewFlow()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            reviewManager.launchReviewFlow(activity, it.result)
                        }
                    }
            }
        }.onFailure {
            it.makeLog("showReview")
        }
    }

}