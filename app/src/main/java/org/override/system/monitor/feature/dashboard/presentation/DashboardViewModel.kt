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
import org.override.system.monitor.feature.sensor.domain.model.MissingSensorInfo
import org.override.system.monitor.feature.sensor.domain.model.SensorExplanations
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
import org.override.system.monitor.core.ui.Destination

class DashboardViewModel(
    private val appNavigator: AppNavigator,
    private val getBatteryDataUseCase: GetBatteryDataUseCase,
    private val getMemoryDataUseCase: GetMemoryDataUseCase,
    private val getStorageDataUseCase: GetStorageDataUseCase,
    private val getSystemIdentityDataUseCase: GetSystemIdentityDataUseCase,
    private val getAccelerometerDataUseCase: GetAccelerometerDataUseCase,
    private val getGyroscopeDataUseCase: GetGyroscopeDataUseCase,
    private val getLightDataUseCase: GetLightDataUseCase,
    // Position& Orientation
    private val getMagnetometerDataUseCase: GetMagnetometerDataUseCase,
    private val getProximityDataUseCase: GetProximityDataUseCase,
    private val getRotationVectorDataUseCase: GetRotationVectorDataUseCase,
    // Environmental
    private val getBarometerDataUseCase: GetBarometerDataUseCase,
    private val getAmbientTemperatureDataUseCase: GetAmbientTemperatureDataUseCase,
    private val getHumidityDataUseCase: GetHumidityDataUseCase,
    // Motion & Health
    private val getStepCounterDataUseCase: GetStepCounterDataUseCase,
    private val getLinearAccelerationDataUseCase: GetLinearAccelerationDataUseCase,
    // Missing sensors
    private val getMissingSensorsUseCase: GetMissingSensorsUseCase
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
        // Check for missing sensors once
        val missingSensorTypes = getMissingSensorsUseCase(SensorExplanations.allNewSensorTypes)
        val missingSensorInfoList = missingSensorTypes.map { sensorType ->
            MissingSensorInfo(
                sensorType = sensorType,
                sensorName = SensorExplanations.getSensorName(sensorType),
                explanation = SensorExplanations.getExplanation(sensorType)
            )
        }
        _state.update { it.copy(missingSensors = missingSensorInfoList) }

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
        // Position& Orientation
        viewModelScope.launch {
            getMagnetometerDataUseCase().collect { data ->
                _state.update { it.copy(magnetometerData = data) }
            }
        }
        viewModelScope.launch {
            getProximityDataUseCase().collect { data ->
                _state.update { it.copy(proximityData = data) }
            }
        }
        viewModelScope.launch {
            getRotationVectorDataUseCase().collect { data ->
                _state.update { it.copy(rotationVectorData = data) }
            }
        }
        // Environmental
        viewModelScope.launch {
            getBarometerDataUseCase().collect { data ->
                _state.update { it.copy(barometerData = data) }
            }
        }
        viewModelScope.launch {
            getAmbientTemperatureDataUseCase().collect { data ->
                _state.update { it.copy(ambientTemperatureData = data) }
            }
        }
        viewModelScope.launch {
            getHumidityDataUseCase().collect { data ->
                _state.update { it.copy(humidityData = data) }
            }
        }
        // Motion & Health
        viewModelScope.launch {
            getStepCounterDataUseCase().collect { data ->
                _state.update { it.copy(stepCounterData = data) }
            }
        }
        viewModelScope.launch {
            getLinearAccelerationDataUseCase().collect { data ->
                _state.update { it.copy(linearAccelerationData = data) }
            }
        }
    }
}
