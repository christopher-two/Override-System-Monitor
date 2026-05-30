package org.override.system.monitor.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.override.system.monitor.feature.battery.domain.usecase.GetBatteryDataUseCase
import org.override.system.monitor.feature.memory.domain.usecase.GetMemoryDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetAccelerometerDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetGyroscopeDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetLightDataUseCase
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
}