/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.di

import com.t8rin.imagetoolbox.core.domain.saving.FailureNotifier
import com.t8rin.imagetoolbox.core.ui.utils.helper.AppToastHost
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FailureNotifierModule {

    @Provides
    @Singleton
    fun notifier(): FailureNotifier = FailureNotifier(AppToastHost::showFailureToast)

}