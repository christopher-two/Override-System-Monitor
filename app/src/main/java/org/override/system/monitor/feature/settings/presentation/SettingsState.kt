package org.override.system.monitor.feature.settings.presentation

data class SettingsState(
    val appVersion: String = "1.0.0"
)

sealed class SettingsAction {
    data object NavigateBack : SettingsAction()
    data object ResetDashboard : SettingsAction()
}