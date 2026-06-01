package org.override.system.monitor.feature.memory.domain.model

data class MemoryData(
    val totalMemory: Long,
    val availableMemory: Long,
    val usedMemory: Long,
    val percentageUsed: Float
) {
    val totalGB: Float get() = totalMemory / 1024f / 1024f / 1024f
    val availableGB: Float get() = availableMemory / 1024f / 1024f / 1024f
    val usedGB: Float get() = usedMemory / 1024f / 1024f / 1024f
}