package org.override.system.monitor.feature.sensor.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.navigation.navigator.AppNavigator

class SensorViewModel(
    private val appNavigator: AppNavigator,
    private val context: Context
) : ViewModel() {

    private val sensorManager = context.getSystemService(android.content.Context.SENSOR_SERVICE) as SensorManager
    val sensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

    private val _action = MutableStateFlow<SensorAction?>(null)
    val action: StateFlow<SensorAction?> = _action.asStateFlow()

    fun processAction(action: SensorAction) {
        when (action) {
            is SensorAction.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        appNavigator.back()
    }
}