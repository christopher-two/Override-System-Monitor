package org.override.system.monitor.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.override.system.monitor.feature.battery.domain.usecase.GetBatteryDataUseCase
import org.override.system.monitor.feature.memory.domain.usecase.GetMemoryDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetAccelerometerDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetGyroscopeDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetLightDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetMagnetometerDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetProximityDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetRotationVectorDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetBarometerDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetAmbientTemperatureDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetHumidityDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetStepCounterDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetLinearAccelerationDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetMissingSensorsUseCase
import org.override.system.monitor.feature.storage.domain.usecase.GetStorageDataUseCase
import org.override.system.monitor.feature.systemidentity.domain.usecase.GetSystemIdentityDataUseCase

val domainModule = module {
    factoryOf(::GetBatteryDataUseCase)
    factoryOf(::GetMemoryDataUseCase)
    factoryOf(::GetStorageDataUseCase)
    factoryOf(::GetSystemIdentityDataUseCase)
    factoryOf(::GetAccelerometerDataUseCase)
    factoryOf(::GetGyroscopeDataUseCase)
    factoryOf(::GetLightDataUseCase)
    // Position& Orientation
    factoryOf(::GetMagnetometerDataUseCase)
    factoryOf(::GetProximityDataUseCase)
    factoryOf(::GetRotationVectorDataUseCase)
    // Environmental
    factoryOf(::GetBarometerDataUseCase)
    factoryOf(::GetAmbientTemperatureDataUseCase)
    factoryOf(::GetHumidityDataUseCase)
    // Motion & Health
    factoryOf(::GetStepCounterDataUseCase)
    factoryOf(::GetLinearAccelerationDataUseCase)
    // Missing sensors
    factoryOf(::GetMissingSensorsUseCase)
}
