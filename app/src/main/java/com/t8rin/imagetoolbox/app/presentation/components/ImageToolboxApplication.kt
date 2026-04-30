/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components

import com.t8rin.imagetoolbox.app.presentation.components.functions.attachLogWriter
import com.t8rin.imagetoolbox.app.presentation.components.functions.initCollages
import com.t8rin.imagetoolbox.app.presentation.components.functions.initColorNames
import com.t8rin.imagetoolbox.app.presentation.components.functions.initNeuralTool
import com.t8rin.imagetoolbox.app.presentation.components.functions.initOpenCV
import com.t8rin.imagetoolbox.app.presentation.components.functions.initPdfBox
import com.t8rin.imagetoolbox.app.presentation.components.functions.initQrScanner
import com.t8rin.imagetoolbox.app.presentation.components.functions.injectBaseComponent
import com.t8rin.imagetoolbox.app.presentation.components.functions.registerSecurityProviders
import com.t8rin.imagetoolbox.app.presentation.components.functions.setupFlags
import com.t8rin.imagetoolbox.app.presentation.components.utils.isMain
import com.t8rin.imagetoolbox.core.crash.presentation.components.applyGlobalExceptionHandler
import com.t8rin.imagetoolbox.core.domain.coroutines.AppScope
import com.t8rin.imagetoolbox.core.domain.remote.AnalyticsManager
import com.t8rin.imagetoolbox.core.domain.saving.KeepAliveService
import com.t8rin.imagetoolbox.core.ui.utils.ComposeApplication
import com.t8rin.imagetoolbox.core.utils.initAppContext
import dagger.hilt.android.HiltAndroidApp
import io.ktor.client.HttpClient
import javax.inject.Inject


@HiltAndroidApp
class ImageToolboxApplication : ComposeApplication() {

    @Inject
    lateinit var keepAliveService: KeepAliveService

    @Inject
    lateinit var appScope: AppScope

    @Inject
    lateinit var httpClient: HttpClient

    @Inject
    lateinit var analyticsManager: AnalyticsManager

    private var isSetupCompleted: Boolean = false

    override fun onCreate() {
        super.onCreate()
        runSetup()
    }

    override fun runSetup() {
        if (isSetupCompleted) return

        if (isMain()) {
            setupFlags()
            initAppContext()
            initOpenCV()
            initNeuralTool()
            initColorNames()
            initQrScanner()
            attachLogWriter()
            applyGlobalExceptionHandler()
            registerSecurityProviders()
            initPdfBox()
            injectBaseComponent()
            initCollages()

            isSetupCompleted = true
        }
    }

}