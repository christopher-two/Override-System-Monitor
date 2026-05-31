package org.override.system.monitor.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.override.system.monitor.feature.battery.data.datasource.BatteryDataSource
import org.override.system.monitor.feature.battery.data.repository.BatteryRepositoryImpl
import org.override.system.monitor.feature.battery.domain.repository.BatteryRepository
import org.override.system.monitor.feature.memory.data.datasource.MemoryDataSource
import org.override.system.monitor.feature.memory.data.repository.MemoryRepositoryImpl
import org.override.system.monitor.feature.memory.domain.repository.MemoryRepository
import org.override.system.monitor.feature.network.data.datasource.NetworkDataSource
import org.override.system.monitor.feature.network.data.repository.NetworkRepositoryImpl
import org.override.system.monitor.feature.network.domain.repository.NetworkRepository
import org.override.system.monitor.feature.sensor.data.datasource.SensorDataSource
import org.override.system.monitor.feature.sensor.data.repository.SensorRepositoryImpl
import org.override.system.monitor.feature.sensor.domain.repository.SensorRepository
import org.override.system.monitor.feature.storage.data.datasource.StorageDataSource
import org.override.system.monitor.feature.storage.data.repository.StorageRepositoryImpl
import org.override.system.monitor.feature.storage.domain.repository.StorageRepository
import org.override.system.monitor.feature.systemidentity.data.datasource.SystemIdentityDataSource
import org.override.system.monitor.feature.systemidentity.data.repository.SystemIdentityRepositoryImpl
import org.override.system.monitor.feature.systemidentity.domain.repository.SystemIdentityRepository

val dataModule = module {
    singleOf(::BatteryDataSource)
    singleOf(::MemoryDataSource)
    singleOf(::StorageDataSource)
    single { SensorDataSource(androidContext(), get()) }
    singleOf(::SystemIdentityDataSource)
    single { NetworkDataSource(androidContext()) }

    singleOf(::BatteryRepositoryImpl) bind BatteryRepository::class
    singleOf(::MemoryRepositoryImpl) bind MemoryRepository::class
    singleOf(::StorageRepositoryImpl) bind StorageRepository::class
    singleOf(::SensorRepositoryImpl) bind SensorRepository::class
    singleOf(::SystemIdentityRepositoryImpl) bind SystemIdentityRepository::class
    singleOf(::NetworkRepositoryImpl) bind NetworkRepository::class
}
