package org.override.system.monitor.core.preferences

import kotlinx.coroutines.flow.Flow

interface IPreferencesRepository {
    val preferencesFlow: Flow<AppPreferences>
    val themeModeFlow: Flow<ThemeMode>
    suspend fun updateThemeMode(mode: ThemeMode)
    suspend fun updateAutoRefresh(enabled: Boolean)
    suspend fun updateRefreshInterval(interval: Int)
    suspend fun updateHighPrecision(enabled: Boolean)
    suspend fun updateShowUnits(enabled: Boolean)
    suspend fun updateVibrationFeedback(enabled: Boolean)
    suspend fun resetPreferences()
}