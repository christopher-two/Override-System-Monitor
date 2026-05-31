package org.override.system.monitor.feature.settings.presentation

data class SettingsState(
    val appVersion: String = "1.0.0",
    val isDarkMode: Boolean = false,
    val isAutoRefresh: Boolean = true,
    val refreshInterval: Int = 1,
    val isHighPrecision: Boolean = false,
    val isShowUnits: Boolean = true,
    val isVibrationFeedback: Boolean = true,
    val showResetDialog: Boolean = false,
    val isLoading: Boolean = true
)

sealed class SettingsAction {
    data object NavigateBack : SettingsAction()
    data object ResetDashboard : SettingsAction()
    data object ShowResetDialog : SettingsAction()
    data object HideResetDialog : SettingsAction()
    data class ToggleDarkMode(val enabled: Boolean) : SettingsAction()
    data class ToggleAutoRefresh(val enabled: Boolean) : SettingsAction()
    data class SetRefreshInterval(val interval: Int) : SettingsAction()
    data class ToggleHighPrecision(val enabled: Boolean) : SettingsAction()
    data class ToggleShowUnits(val enabled: Boolean) : SettingsAction()
    data class ToggleVibrationFeedback(val enabled: Boolean) : SettingsAction()
    data object OpenPrivacyPolicy : SettingsAction()
    data object OpenTermsAndConditions : SettingsAction()
}