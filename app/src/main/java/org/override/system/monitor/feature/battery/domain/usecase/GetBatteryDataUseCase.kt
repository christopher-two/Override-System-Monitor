package org.override.system.monitor.feature.battery.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.battery.domain.model.BatteryData
import org.override.system.monitor.feature.battery.domain.repository.BatteryRepository

class GetBatteryDataUseCase(private val repository: BatteryRepository) {
    operator fun invoke(): Flow<BatteryData> = repository.batteryData
}