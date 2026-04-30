/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.ai_tools.data.model

import ai.onnxruntime.NodeInfo
import ai.onnxruntime.OnnxJavaType
import ai.onnxruntime.OrtSession
import ai.onnxruntime.TensorInfo
import com.t8rin.imagetoolbox.core.utils.makeLog
import com.t8rin.imagetoolbox.feature.ai_tools.domain.model.NeuralModel
import kotlin.math.abs

internal class ModelInfo(
    val strength: Float,
    val overlap: Int,
    chunkSize: Int,
    disableChunking: Boolean,
    session: OrtSession,
    model: NeuralModel
) {
    val inputName: String
    val inputInfoMap: Map<String, NodeInfo> = session.inputInfo
    val inputChannels: Int
    val outputChannels: Int
    val isFp16: Boolean
    val expectedWidth: Int?
    val expectedHeight: Int?
    val isScuNet = model.name.startsWith("scunet_")
    val isScuNetColor = model.name.startsWith("scunet_color")
    val isNonChunkable = model.isNonChunkable || disableChunking
    val chunkSize = if (isScuNet) {
        minOf(chunkSize, 256)
    } else {
        chunkSize
    }
    val minSpatialSize = getMinSpatialSize(model.name)

    val scaleFactor: Int = scaleMap.entries.find {
        model.name.contains(it.key)
    }?.value ?: 1

    init {
        "Initialized with chunkSize: $chunkSize, overlap: $overlap"
            .makeLog("ModelInfo")
        var foundInputName: String? = null
        var foundInputChannels = 3
        var foundOutputChannels = 3
        var foundIsFp16 = false
        var foundExpectedWidth: Int? = null
        var foundExpectedHeight: Int? = null

        for ((key, nodeInfo) in inputInfoMap) {
            val tensorInfo = nodeInfo.info as? TensorInfo ?: continue
            val shape = tensorInfo.shape
            if ((tensorInfo.type == OnnxJavaType.FLOAT || tensorInfo.type == OnnxJavaType.FLOAT16) && shape.size == 4) {
                foundInputName = key
                foundInputChannels = if (shape[1] == 1L) 1 else 3
                foundIsFp16 = (tensorInfo.type == OnnxJavaType.FLOAT16)
                if (shape[2] > 0) foundExpectedHeight = shape[2].toInt()
                if (shape[3] > 0) foundExpectedWidth = shape[3].toInt()
                break
            }
        }

        val outputInfoMap = session.outputInfo
        for ((_, nodeInfo) in outputInfoMap) {
            val tensorInfo = nodeInfo.info as? TensorInfo ?: continue
            val shape = tensorInfo.shape
            if ((tensorInfo.type == OnnxJavaType.FLOAT || tensorInfo.type == OnnxJavaType.FLOAT16) && shape.size == 4) {
                foundOutputChannels = if (isScuNet) {
                    if (shape[1] == 1L) 1 else 3
                } else {
                    if (abs(shape[1]) == 1L) 1 else 3
                }
                break
            }
        }
        inputName = foundInputName ?: throw RuntimeException("Could not find valid input tensor")
        inputChannels = foundInputChannels
        outputChannels = foundOutputChannels
        isFp16 = foundIsFp16
        expectedWidth = foundExpectedWidth
        expectedHeight = foundExpectedHeight

        "Model input type: ${if (isFp16) "FP16" else "FP32"}, input channels: $inputChannels, output channels: $outputChannels, expected dimensions: ${expectedWidth ?: "dynamic"}x${expectedHeight ?: "dynamic"}, scaleFactor: $scaleFactor"
            .makeLog("ModelInfo")
    }
}

private val scaleMap = buildMap {
    repeat(16) {
        val scale = it + 1

        put("x$scale", scale)
        put("${scale}x", scale)
    }
}

private val minSpatial = mapOf(
    "nafnet" to 512
)

private fun getMinSpatialSize(modelName: String?): Int {
    val normalized = modelName?.lowercase() ?: return 256
    for ((pattern, size) in minSpatial) {
        if (normalized.contains(pattern)) {
            return size
        }
    }
    return 256
}