package org.override.system.monitor.feature.battery.domain.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.battery.domain.model.BatteryData

interface BatteryRepository {
    val batteryData: Flow<BatteryData>
}