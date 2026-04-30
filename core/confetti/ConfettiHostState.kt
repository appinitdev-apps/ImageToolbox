/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.utils.confetti

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.t8rin.imagetoolbox.core.settings.domain.model.ColorHarmonizer
import com.t8rin.imagetoolbox.core.settings.presentation.provider.LocalSettingsState
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import com.t8rin.imagetoolbox.core.ui.widget.other.ToastDuration
import com.t8rin.imagetoolbox.core.ui.widget.other.ToastHost
import com.t8rin.imagetoolbox.core.ui.widget.other.ToastHostState
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party

@Stable
@Immutable
class ConfettiHostState : ToastHostState() {
    suspend fun showConfetti(
        duration: ToastDuration = ToastDuration(4500L)
    ) = showToast(message = "", duration = duration)
}

@Composable
fun ConfettiHost(
    hostState: ConfettiHostState,
    particles: @Composable (harmonizer: Color) -> List<Party>
) {
    ToastHost(
        hostState = hostState,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        toast = {
            val settingsState = LocalSettingsState.current
            val colorScheme = MaterialTheme.colorScheme
            val confettiHarmonizationLevel = settingsState.confettiHarmonizationLevel
            val harmonizationColor = when (
                val harmonizer = settingsState.confettiColorHarmonizer
            ) {
                is ColorHarmonizer.Custom -> Color(harmonizer.color)
                ColorHarmonizer.Primary -> colorScheme.primary
                ColorHarmonizer.Secondary -> colorScheme.secondary
                ColorHarmonizer.Tertiary -> colorScheme.tertiary
            }
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = particles(harmonizationColor.copy(confettiHarmonizationLevel))
            )
        },
        enableSwipes = false
    )
}

@Composable
fun ConfettiHost() {
    val settingsState = LocalSettingsState.current

    AnimatedVisibility(settingsState.isConfettiEnabled) {
        ConfettiHost(
            hostState = AppToastHost.confettiState,
            particles = { harmonizer ->
                val particlesType by remember(settingsState.confettiType) {
                    derivedStateOf {
                        Particles.Type.entries.first {
                            it.ordinal == settingsState.confettiType
                        }
                    }
                }

                remember {
                    Particles(
                        harmonizer = harmonizer
                    ).build(particlesType)
                }
            }
        )
    }

    if (!settingsState.isConfettiEnabled) {
        SideEffect {
            AppToastHost.confettiState.currentToastData?.dismiss()
        }
    }
}