/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.app.presentation.components.functions

import com.t8rin.imagetoolbox.core.domain.model.CipherType
import com.t8rin.imagetoolbox.core.domain.model.HashingType
import com.t8rin.imagetoolbox.core.utils.makeLog
import org.bouncycastle.asn1.ASN1ObjectIdentifier
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.DefaultAlgorithmNameFinder
import java.security.Provider
import java.security.Security

internal fun registerSecurityProviders() {
    initBouncyCastle()

    HashingType.registerSecurityMessageDigests(
        Security.getAlgorithms("MessageDigest").filterNotNull()
    )

    val finder = DefaultAlgorithmNameFinder()

    CipherType.registerSecurityCiphers(
        Security.getAlgorithms("Cipher").filterNotNull().mapNotNull { cipher ->
            if (CipherType.BROKEN.any { cipher.contains(it, true) }) return@mapNotNull null

            val oid = cipher.removePrefix("OID.")
            if (oid.all { it.isDigit() || it.isWhitespace() || it == '.' }) {
                CipherType.getInstance(
                    cipher = cipher,
                    name = finder.getAlgorithmName(
                        ASN1ObjectIdentifier(oid)
                    )
                )
            } else {
                CipherType.getInstance(
                    cipher = cipher
                )
            }.also {
                val extraExclude = it.cipher == "DES"
                        || it.name == "DES/CBC"
                        || it.name == "THREEFISH-512"
                        || it.name == "THREEFISH-1024"
                        || it.name == "CCM"

                if (extraExclude) return@mapNotNull null
            }
        }
    )
}

private fun initBouncyCastle() {
    if (Security.getProvider(WORKAROUND_NAME) != null) return

    try {
        logProviders("OLD")

        Security.addProvider(BouncyCastleWorkaroundProvider())

        logProviders("NEW")
    } catch (e: Throwable) {
        e.makeLog()
        "Failed to register BouncyCastleWorkaroundProvider".makeLog()
    }
}

private fun logProviders(tag: String): Int {
    val providers = Security.getProviders()
    providers.forEachIndexed { index, provider ->
        "$tag [$index]: ${provider.name} - ${provider.info}".makeLog("Providers")
    }
    return providers.size
}

private class BouncyCastleWorkaroundProvider(
    bouncyCastleProvider: Provider = BouncyCastleProvider()
) : Provider(
    WORKAROUND_NAME,
    bouncyCastleProvider.version,
    bouncyCastleProvider.info
) {
    init {
        for ((key, value) in bouncyCastleProvider.entries) {
            put(key.toString(), value.toString())
        }
    }
}

private const val WORKAROUND_NAME = "BC_WORKAROUND"