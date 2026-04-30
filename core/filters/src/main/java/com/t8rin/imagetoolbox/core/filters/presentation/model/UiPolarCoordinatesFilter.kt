/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.enums.PolarCoordinatesType
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.DISTORTION)
class UiPolarCoordinatesFilter(
    override val value: PolarCoordinatesType = PolarCoordinatesType.RECT_TO_POLAR
) : UiFilter<PolarCoordinatesType>(
    title = R.string.polar_coordinates,
    value = value
), Filter.PolarCoordinates