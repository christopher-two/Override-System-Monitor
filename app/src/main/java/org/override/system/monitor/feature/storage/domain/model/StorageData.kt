package org.override.system.monitor.feature.storage.domain.model

data class StorageData(
    val totalStorage: Long,
    val availableStorage: Long,
    val usedStorage: Long,
    val percentageUsed: Float
) {
    val totalGB: Float get() = totalStorage / 1024f / 1024f / 1024f
    val availableGB: Float get() = availableStorage / 1024f / 1024f / 1024f
    val usedGB: Float get() = usedStorage / 1024f / 1024f / 1024f
}