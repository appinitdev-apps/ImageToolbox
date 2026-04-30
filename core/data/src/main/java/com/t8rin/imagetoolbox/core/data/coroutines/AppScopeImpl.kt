/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.coroutines

import com.t8rin.imagetoolbox.core.domain.coroutines.AppScope
import com.t8rin.imagetoolbox.core.domain.coroutines.DispatchersHolder
import com.t8rin.imagetoolbox.core.utils.makeLog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


internal class AppScopeImpl @Inject constructor(
    dispatchersHolder: DispatchersHolder
) : AppScope, DispatchersHolder by dispatchersHolder {
    override val coroutineContext: CoroutineContext =
        defaultDispatcher + CoroutineExceptionHandler { _, e -> e.makeLog("AppScopeImpl") } + SupervisorJob()
}