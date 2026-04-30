/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.crash.presentation.components

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.crash.presentation.screenLogic.CrashComponent
import com.t8rin.imagetoolbox.core.settings.presentation.model.toUiState
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppActivityClass
import com.t8rin.imagetoolbox.core.ui.utils.helper.Clipboard
import com.t8rin.imagetoolbox.core.ui.utils.provider.ImageToolboxCompositionLocals
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.enhancedVerticalScroll

@Composable
internal fun CrashRootContent(component: CrashComponent) {
    /*val context = LocalContext.current
    val crashInfo = component.crashInfo


    ImageToolboxCompositionLocals(
        settingsState = component.settingsState.toUiState()
    ) {
        val copyCrashInfo: () -> Unit = {
            Clipboard.copy(crashInfo.textToSend)
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .enhancedVerticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 80.dp)
                .navigationBarsPadding()
                .displayCutoutPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CrashAttentionCard()
            Spacer(modifier = Modifier.height(24.dp))
            CrashActionButtons(
                onCopyCrashInfo = copyCrashInfo,
                onShareLogs = component::shareLogs,
                githubLink = crashInfo.githubLink
            )
            Spacer(modifier = Modifier.height(24.dp))
            CrashInfoCard(crashInfo = crashInfo)
        }

        CrashBottomButtons(
            modifier = Modifier.align(Alignment.BottomCenter),
            onCopy = copyCrashInfo,
            onRestartApp = {
                context.startActivity(
                    Intent(context, AppActivityClass)
                )
            }
        )
    }*/
}