/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.domain

data class MarkupProject(
    val background: ProjectBackground,
    val layers: List<MarkupLayer>,
    val lastLayers: List<MarkupLayer>,
    val undoneLayers: List<MarkupLayer>,
    val history: List<MarkupProjectHistorySnapshot> = emptyList(),
    val redoHistory: List<MarkupProjectHistorySnapshot> = emptyList(),
)

data class MarkupProjectHistorySnapshot(
    val background: ProjectBackground,
    val layers: List<MarkupLayer>,
)