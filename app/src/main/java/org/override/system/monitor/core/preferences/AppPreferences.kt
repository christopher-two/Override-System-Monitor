package org.override.system.monitor.core.preferences

data class AppPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isAutoRefresh: Boolean = true,
    val refreshInterval: Int = 1,
    val isHighPrecision: Boolean = false,
    val isShowUnits: Boolean = true,
    val isVibrationFeedback: Boolean = true
)