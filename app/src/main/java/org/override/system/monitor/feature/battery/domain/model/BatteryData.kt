package org.override.system.monitor.feature.battery.domain.model

data class BatteryData(
    val percentage: Int,
    val temperature: Float,
    val status: String,
    val voltage: Int,
    val health: String,
    val isCharging: Boolean
)