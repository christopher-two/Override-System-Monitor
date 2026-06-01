package org.override.system.monitor.feature.battery.presentation

import org.override.system.monitor.feature.battery.domain.model.BatteryData

data class BatteryState(
    val data: BatteryData? = null,
    val isLoading: Boolean = true
)