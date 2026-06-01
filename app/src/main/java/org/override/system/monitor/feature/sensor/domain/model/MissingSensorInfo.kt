package org.override.system.monitor.feature.sensor.domain.model

data class MissingSensorInfo(
    val sensorType: Int,
    val sensorNameResId: Int,
    val explanationResId: Int
)