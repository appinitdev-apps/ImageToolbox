/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.data.di

import com.t8rin.imagetoolbox.core.data.remote.AndroidDownloadManager
import com.t8rin.imagetoolbox.core.data.remote.AndroidRemoteResourcesStore
import com.t8rin.imagetoolbox.core.domain.remote.DownloadManager
import com.t8rin.imagetoolbox.core.domain.remote.RemoteResourcesStore
import com.t8rin.imagetoolbox.core.utils.makeLog
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import javax.inject.Singleton
import kotlin.time.Duration.Companion.minutes

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteModule {

    @Binds
    @Singleton
    fun remoteResources(
        impl: AndroidRemoteResourcesStore
    ): RemoteResourcesStore

    @Binds
    @Singleton
    fun downloadManager(
        impl: AndroidDownloadManager
    ): DownloadManager

    companion object {
        @Provides
        @Singleton
        fun client(): HttpClient = HttpClient {
            install(HttpTimeout) {
                val timeout = 5.minutes.inWholeMilliseconds
                requestTimeoutMillis = timeout
                connectTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        message.makeLog("Ktor")
                    }
                }
                level = LogLevel.HEADERS
            }
        }
    }

}