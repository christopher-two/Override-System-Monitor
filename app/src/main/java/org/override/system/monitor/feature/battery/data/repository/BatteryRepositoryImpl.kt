package org.override.system.monitor.feature.battery.data.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.battery.data.datasource.BatteryDataSource
import org.override.system.monitor.feature.battery.domain.model.BatteryData
import org.override.system.monitor.feature.battery.domain.repository.BatteryRepository

class BatteryRepositoryImpl(private val dataSource: BatteryDataSource) : BatteryRepository {
    override val batteryData: Flow<BatteryData> = dataSource.getBatteryData()
}