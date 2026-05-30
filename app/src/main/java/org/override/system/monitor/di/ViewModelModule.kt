package org.override.system.monitor.di

import android.content.Context
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.override.system.monitor.feature.battery.presentation.BatteryViewModel
import org.override.system.monitor.feature.dashboard.presentation.DashboardViewModel
import org.override.system.monitor.feature.memory.presentation.MemoryViewModel
import org.override.system.monitor.feature.navigation.navigator.AppNavigator
import org.override.system.monitor.feature.sensor.presentation.SensorViewModel
import org.override.system.monitor.feature.settings.presentation.SettingsViewModel
import org.override.system.monitor.feature.storage.presentation.StorageViewModel

val viewModelModule = module {
    viewModelOf(::DashboardViewModel)
    viewModelOf(::BatteryViewModel)
    viewModelOf(::MemoryViewModel)
    viewModelOf(::StorageViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::SensorViewModel)
}