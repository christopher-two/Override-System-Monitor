package org.override.system.monitor.feature.sensor.presentation

import android.hardware.Sensor

data class SensorState(
    val sensors: List<Sensor> = emptyList()
)

sealed class SensorAction {
    data object NavigateBack : SensorAction()
}