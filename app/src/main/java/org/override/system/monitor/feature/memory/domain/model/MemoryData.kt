package org.override.system.monitor.feature.memory.domain.model

data class MemoryData(
    val totalMemory: Long,
    val availableMemory: Long,
    val usedMemory: Long,
    val percentageUsed: Float
)