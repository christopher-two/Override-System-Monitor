package org.override.system.monitor.feature.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.battery.domain.usecase.GetBatteryDataUseCase
import org.override.system.monitor.feature.cpu.domain.usecase.GetCpuDataUseCase
import org.override.system.monitor.feature.dashboard.domain.usecase.DashboardSensorUseCase
import org.override.system.monitor.feature.memory.domain.usecase.GetMemoryDataUseCase
import org.override.system.monitor.feature.network.domain.usecase.GetNetworkDataUseCase
import org.override.system.monitor.feature.navigation.navigator.AppNavigator
import org.override.system.monitor.feature.storage.domain.usecase.GetStorageDataUseCase
import org.override.system.monitor.feature.systemidentity.domain.usecase.GetSystemIdentityDataUseCase
import org.override.system.monitor.core.ui.Destination

class DashboardViewModel(
    private val appNavigator: AppNavigator,
    private val getBatteryDataUseCase: GetBatteryDataUseCase,
    private val getCpuDataUseCase: GetCpuDataUseCase,
    private val getMemoryDataUseCase: GetMemoryDataUseCase,
    private val getStorageDataUseCase: GetStorageDataUseCase,
    private val getSystemIdentityDataUseCase: GetSystemIdentityDataUseCase,
    private val dashboardSensorUseCase: DashboardSensorUseCase,
    private val getNetworkDataUseCase: GetNetworkDataUseCase
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
            is DashboardAction.RefreshNetworkPermission -> refreshNetworkPermission()
        }
    }

    private fun refreshNetworkPermission() {
        _state.update { it.copy(hasNetworkPermission = getNetworkDataUseCase.hasNetworkPermissions()) }
    }

    private fun navigate(destination: Destination) {
        appNavigator.navigateTo(destination)
    }

    private fun loadData() {
        val missingSensors = dashboardSensorUseCase.getMissingSensorsInfo()
        _state.update { it.copy(missingSensors = missingSensors) }

        viewModelScope.launch {
            getBatteryDataUseCase().collect { data ->
                _state.update { it.copy(batteryData = data, isLoading = false) }
            }
        }
        viewModelScope.launch {
            getCpuDataUseCase().collect { data ->
                _state.update { it.copy(cpuData = data) }
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
        launchSensorCollection(dashboardSensorUseCase.getAccelerometerFlow()) { data -> _state.update { it.copy(accelerometerData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getGyroscopeFlow()) { data -> _state.update { it.copy(gyroscopeData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getLightFlow()) { data -> _state.update { it.copy(lightData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getMagnetometerFlow()) { data -> _state.update { it.copy(magnetometerData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getProximityFlow()) { data -> _state.update { it.copy(proximityData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getRotationVectorFlow()) { data -> _state.update { it.copy(rotationVectorData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getBarometerFlow()) { data -> _state.update { it.copy(barometerData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getAmbientTemperatureFlow()) { data -> _state.update { it.copy(ambientTemperatureData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getHumidityFlow()) { data -> _state.update { it.copy(humidityData = data) } }
        launchSensorCollection(dashboardSensorUseCase.getLinearAccelerationFlow()) { data -> _state.update { it.copy(linearAccelerationData = data) } }
        viewModelScope.launch {
            _state.update { it.copy(hasNetworkPermission = getNetworkDataUseCase.hasNetworkPermissions()) }
        }
        viewModelScope.launch {
            getNetworkDataUseCase().collect { data ->
                _state.update { it.copy(networkData = data) }
            }
        }
    }

    private fun launchSensorCollection(flow: kotlinx.coroutines.flow.Flow<org.override.system.monitor.core.common.model.SensorData>, update: (org.override.system.monitor.core.common.model.SensorData) -> Unit) {
        viewModelScope.launch {
            flow.collect { data -> update(data) }
        }
    }
}