package org.override.system.monitor.feature.sensor.presentation

sealed class SensorAction {
    data object NavigateBack : SensorAction()
}