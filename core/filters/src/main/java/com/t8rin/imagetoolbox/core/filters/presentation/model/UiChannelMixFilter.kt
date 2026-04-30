/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.filters.presentation.model

import com.t8rin.imagetoolbox.core.filters.domain.model.Filter
import com.t8rin.imagetoolbox.core.filters.domain.model.FilterParam
import com.t8rin.imagetoolbox.core.filters.domain.model.params.ChannelMixParams
import com.t8rin.imagetoolbox.core.ksp.annotations.UiFilterInject
import com.t8rin.imagetoolbox.core.resources.R

@UiFilterInject(group = UiFilterInject.Groups.COLOR)
class UiChannelMixFilter(
    override val value: ChannelMixParams = ChannelMixParams.Default
) : UiFilter<ChannelMixParams>(
    title = R.string.channel_mix,
    paramsInfo = listOf(
        FilterParam(R.string.blue_green, 0f..255f, 0),
        FilterParam(R.string.red_blue, 0f..255f, 0),
        FilterParam(R.string.green_red, 0f..255f, 0),
        FilterParam(R.string.into_red, 0f..255f, 0),
        FilterParam(R.string.into_green, 0f..255f, 0),
        FilterParam(R.string.into_blue, 0f..255f, 0),
    ),
    value = value
), Filter.ChannelMix