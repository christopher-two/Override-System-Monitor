package org.override.system.monitor.feature.settings.presentation

import org.override.system.monitor.core.preferences.ThemeMode

data class SettingsState(
    val appVersion: String = "1.0.0",
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isAutoRefresh: Boolean = true,
    val refreshInterval: Int = 1,
    val isHighPrecision: Boolean = false,
    val isShowUnits: Boolean = true,
    val isVibrationFeedback: Boolean = true,
    val showResetDialog: Boolean = false,
    val isLoading: Boolean = true
)