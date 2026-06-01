package org.override.system.monitor.feature.battery.presentation

sealed class BatteryAction {
    data object NavigateBack : BatteryAction()
}