/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.helper

import android.app.Activity
import com.t8rin.imagetoolbox.core.utils.makeLog
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class ReviewHandler {

    private val _reviewRequests: Channel<Unit> = Channel(Channel.BUFFERED)
    val reviewRequests: Flow<Unit> = _reviewRequests.receiveAsFlow()

    fun requestReview() {
        makeLog("requestReview")
        _reviewRequests.trySend(Unit)
    }

    open val showNotShowAgainButton: Boolean = false

    open fun notShowReviewAgain() = Unit

    abstract fun showReview(activity: Activity)

    companion object {
        val current: ReviewHandler = ReviewHandlerImpl
    }

}