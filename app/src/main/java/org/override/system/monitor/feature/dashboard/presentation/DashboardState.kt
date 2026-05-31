package org.override.system.monitor.feature.dashboard.presentation

import org.override.system.monitor.core.common.model.SensorData
import org.override.system.monitor.feature.battery.domain.model.BatteryData
import org.override.system.monitor.feature.memory.domain.model.MemoryData
import org.override.system.monitor.feature.sensor.domain.model.MissingSensorInfo
import org.override.system.monitor.feature.storage.domain.model.StorageData
import org.override.system.monitor.feature.systemidentity.domain.model.SystemIdentityData

data class DashboardState(
    val batteryData: BatteryData? = null,
    val memoryData: MemoryData? = null,
    val storageData: StorageData? = null,
    val systemIdentityData: SystemIdentityData? = null,
    val accelerometerData: SensorData? = null,
    val gyroscopeData: SensorData? = null,
    val lightData: SensorData? = null,
    // Position& Orientation
    val magnetometerData: SensorData? = null,
    val proximityData: SensorData? = null,
    val rotationVectorData: SensorData? = null,
    // Environmental
    val barometerData: SensorData? = null,
    val ambientTemperatureData: SensorData? = null,
    val humidityData: SensorData? = null,
    // Motion & Health
    val linearAccelerationData: SensorData? = null,
    // Missing sensors
    val missingSensors: List<MissingSensorInfo> = emptyList(),
    val isLoading: Boolean = true
)
