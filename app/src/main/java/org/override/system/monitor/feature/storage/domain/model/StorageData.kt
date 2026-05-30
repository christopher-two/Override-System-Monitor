package org.override.system.monitor.feature.storage.domain.model

data class StorageData(
    val totalStorage: Long,
    val availableStorage: Long,
    val usedStorage: Long,
    val percentageUsed: Float
)