/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.settings.domain.model

import com.t8rin.imagetoolbox.core.domain.model.HashingType

sealed interface FilenameBehavior {
    class None : FilenameBehavior

    class Overwrite : FilenameBehavior

    class Random : FilenameBehavior

    data class Checksum(
        val hashingType: HashingType
    ) : FilenameBehavior
}