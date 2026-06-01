package org.override.system.monitor.di

import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation
import org.override.system.monitor.feature.battery.presentation.BatteryDetailScreen
import org.override.system.monitor.feature.cpu.presentation.CpuDetailScreen
import org.override.system.monitor.feature.dashboard.presentation.DashboardScreen
import org.override.system.monitor.feature.memory.presentation.MemoryDetailScreen
import org.override.system.monitor.feature.navigation.navigator.AppNavigator
import org.override.system.monitor.feature.sensor.presentation.SensorDetailScreen
import org.override.system.monitor.feature.settings.presentation.SettingsScreen
import org.override.system.monitor.feature.storage.presentation.StorageDetailScreen
import org.override.system.monitor.core.ui.Destination

@OptIn(KoinExperimentalAPI::class)
val navigationModule = module {
    singleOf(::AppNavigator)

    navigation<Destination.Dashboard> {
        DashboardScreen(viewModel = koinViewModel())
    }

    navigation<Destination.BatteryDetail> {
        BatteryDetailScreen(viewModel = koinViewModel())
    }

    navigation<Destination.MemoryDetail> {
        MemoryDetailScreen(viewModel = koinViewModel())
    }

    navigation<Destination.StorageDetail> {
        StorageDetailScreen(viewModel = koinViewModel())
    }

    navigation<Destination.CpuDetail> {
        CpuDetailScreen(viewModel = koinViewModel())
    }

    navigation<Destination.SensorDetail> {
        SensorDetailScreen(viewModel = koinViewModel())
    }

    navigation<Destination.Settings> {
        SettingsScreen(viewModel = koinViewModel())
    }
}