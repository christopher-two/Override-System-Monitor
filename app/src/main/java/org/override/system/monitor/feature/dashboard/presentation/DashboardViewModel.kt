package org.override.system.monitor.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.battery.domain.usecase.GetBatteryDataUseCase
import org.override.system.monitor.feature.memory.domain.usecase.GetMemoryDataUseCase
import org.override.system.monitor.feature.navigation.navigator.AppNavigator
import org.override.system.monitor.feature.sensor.domain.usecase.GetAccelerometerDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetGyroscopeDataUseCase
import org.override.system.monitor.feature.sensor.domain.usecase.GetLightDataUseCase
import org.override.system.monitor.feature.storage.domain.usecase.GetStorageDataUseCase
import org.override.system.monitor.feature.systemidentity.domain.usecase.GetSystemIdentityDataUseCase
import org.override.system.monitor.core.ui.Destination

class DashboardViewModel(
    private val appNavigator: AppNavigator,
    private val getBatteryDataUseCase: GetBatteryDataUseCase,
    private val getMemoryDataUseCase: GetMemoryDataUseCase,
    private val getStorageDataUseCase: GetStorageDataUseCase,
    private val getSystemIdentityDataUseCase: GetSystemIdentityDataUseCase,
    private val getAccelerometerDataUseCase: GetAccelerometerDataUseCase,
    private val getGyroscopeDataUseCase: GetGyroscopeDataUseCase,
    private val getLightDataUseCase: GetLightDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    init {
        processAction(DashboardAction.LoadData)
    }

    fun processAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.LoadData -> loadData()
            is DashboardAction.Navigate -> navigate(action.destination)
        }
    }

    private fun navigate(destination: Destination) {
        appNavigator.navigateTo(destination)
    }

    private fun loadData() {
        viewModelScope.launch {
            getBatteryDataUseCase().collect { data ->
                _state.update { it.copy(batteryData = data, isLoading = false) }
            }
        }
        viewModelScope.launch {
            getMemoryDataUseCase().collect { data ->
                _state.update { it.copy(memoryData = data) }
            }
        }
        viewModelScope.launch {
            getStorageDataUseCase().collect { data ->
                _state.update { it.copy(storageData = data) }
            }
        }
        viewModelScope.launch {
            getSystemIdentityDataUseCase().collect { data ->
                _state.update { it.copy(systemIdentityData = data) }
            }
        }
        viewModelScope.launch {
            getAccelerometerDataUseCase().collect { data ->
                _state.update { it.copy(accelerometerData = data) }
            }
        }
        viewModelScope.launch {
            getGyroscopeDataUseCase().collect { data ->
                _state.update { it.copy(gyroscopeData = data) }
            }
        }
        viewModelScope.launch {
            getLightDataUseCase().collect { data ->
                _state.update { it.copy(lightData = data) }
            }
        }
    }
}