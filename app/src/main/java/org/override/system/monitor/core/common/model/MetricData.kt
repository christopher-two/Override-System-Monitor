package org.override.system.monitor.core.common.model

data class MetricData(
    val total: Long,
    val available: Long,
    val used: Long,
    val percentageUsed: Float
) {
    val totalGB: Float get() = total / 1024f / 1024f / 1024f
    val availableGB: Float get() = available / 1024f / 1024f / 1024f
    val usedGB: Float get() = used / 1024f / 1024f / 1024f
}