/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.markup_layers.data.project

import com.t8rin.imagetoolbox.core.domain.image.model.BlendingMode

data class MarkupProjectFile(
    val version: Int = MarkupProjectVersion,
    val background: BackgroundSnapshot,
    val layers: List<LayerSnapshot>,
    val lastLayers: List<LayerSnapshot>,
    val undoneLayers: List<LayerSnapshot>,
    val history: List<EditorSnapshot> = emptyList(),
    val redoHistory: List<EditorSnapshot> = emptyList(),
)

data class EditorSnapshot(
    val background: BackgroundSnapshot,
    val layers: List<LayerSnapshot>,
)

data class BackgroundSnapshot(
    val type: BackgroundType,
    val assetPath: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val color: Int? = null,
)

data class LayerSnapshot(
    val type: LayerSnapshotType,
    val position: PositionSnapshot,
    val contentWidth: Int? = null,
    val contentHeight: Int? = null,
    val visibleLineCount: Int? = null,
    val cornerRadiusPercent: Int = 0,
    val isLocked: Boolean = false,
    val blendingMode: Int = BlendingMode.SrcOver.value,
    val text: TextSnapshot? = null,
    val picture: PictureSnapshot? = null,
    val shape: ShapeSnapshot? = null,
    val groupedLayers: List<LayerSnapshot> = emptyList(),
)

data class PositionSnapshot(
    val scale: Float,
    val rotation: Float,
    val isFlippedHorizontally: Boolean = false,
    val isFlippedVertically: Boolean = false,
    val offsetX: Float,
    val offsetY: Float,
    val alpha: Float,
    val canvasWidth: Int,
    val canvasHeight: Int,
    val coerceToBounds: Boolean,
    val isVisible: Boolean,
)

data class TextSnapshot(
    val color: Int,
    val size: Float,
    val font: FontSnapshot?,
    val backgroundColor: Int,
    val text: String,
    val decorations: List<String>,
    val outline: OutlineSnapshot?,
    val alignment: String,
    val geometricTransform: TextGeometricTransformSnapshot? = null,
    val shadow: DropShadowSnapshot? = null,
)

data class PictureSnapshot(
    val assetPath: String? = null,
    val value: String? = null,
    val shadow: DropShadowSnapshot? = null,
)

data class ShapeSnapshot(
    val modeName: String? = null,
    val modeOrdinal: Int = 0,
    val color: Int,
    val strokeWidth: Float,
    val widthRatio: Float,
    val heightRatio: Float,
    val fillColor: Int? = null,
    val rotationDegrees: Int? = null,
    val cornerRadius: Float? = null,
    val vertices: Int? = null,
    val isRegular: Boolean? = null,
    val innerRadiusRatio: Float? = null,
    val sizeScale: Float? = null,
    val angle: Float? = null,
    val shadow: DropShadowSnapshot? = null,
)

data class FontSnapshot(
    val type: FontSnapshotType,
    val resourceId: Int? = null,
    val path: String? = null,
    val resourceName: String? = null,
    val familyKey: String? = null,
    val assetPath: String? = null,
    val filename: String? = null,
)

data class OutlineSnapshot(
    val color: Int,
    val width: Float,
)

data class TextGeometricTransformSnapshot(
    val scaleX: Float = 1f,
    val skewX: Float = 0f,
)

data class DropShadowSnapshot(
    val color: Int,
    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val blurRadius: Float = 0f,
)

enum class BackgroundType {
    None, Image, Color
}

enum class LayerSnapshotType {
    Text, Image, Sticker, Shape
}

enum class FontSnapshotType {
    File, Resource
}