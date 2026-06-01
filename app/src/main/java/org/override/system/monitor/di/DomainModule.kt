package org.override.system.monitor.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.override.system.monitor.feature.battery.domain.usecase.GetBatteryDataUseCase
import org.override.system.monitor.feature.memory.domain.usecase.GetMemoryDataUseCase
import org.override.system.monitor.feature.network.domain.usecase.GetNetworkDataUseCase
import org.override.system.monitor.feature.dashboard.domain.usecase.DashboardSensorUseCase
import org.override.system.monitor.feature.sensor.domain.repository.SensorRepository
import org.override.system.monitor.feature.storage.domain.usecase.GetStorageDataUseCase
import org.override.system.monitor.feature.systemidentity.domain.usecase.GetSystemIdentityDataUseCase

val domainModule = module {
    factoryOf(::GetBatteryDataUseCase)
    factoryOf(::GetMemoryDataUseCase)
    factoryOf(::GetStorageDataUseCase)
    factoryOf(::GetSystemIdentityDataUseCase)
    // Dashboard sensor facade
    factory { DashboardSensorUseCase(get<SensorRepository>()) }
    // Network
    factoryOf(::GetNetworkDataUseCase)
}
