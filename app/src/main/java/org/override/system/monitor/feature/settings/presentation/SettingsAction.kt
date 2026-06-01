package org.override.system.monitor.feature.settings.presentation

import org.override.system.monitor.core.preferences.ThemeMode

sealed class SettingsAction {
    data object NavigateBack : SettingsAction()
    data object ResetDashboard : SettingsAction()
    data object ShowResetDialog : SettingsAction()
    data object HideResetDialog : SettingsAction()
    data class SetThemeMode(val mode: ThemeMode) : SettingsAction()
    data class ToggleAutoRefresh(val enabled: Boolean) : SettingsAction()
    data class SetRefreshInterval(val interval: Int) : SettingsAction()
    data class ToggleHighPrecision(val enabled: Boolean) : SettingsAction()
    data class ToggleShowUnits(val enabled: Boolean) : SettingsAction()
    data class ToggleVibrationFeedback(val enabled: Boolean) : SettingsAction()
    data object OpenPrivacyPolicy : SettingsAction()
    data object OpenTermsAndConditions : SettingsAction()
}