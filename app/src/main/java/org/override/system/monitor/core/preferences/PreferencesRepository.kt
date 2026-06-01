package org.override.system.monitor.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

data class AppPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val isAutoRefresh: Boolean = true,
    val refreshInterval: Int = 1,
    val isHighPrecision: Boolean = false,
    val isShowUnits: Boolean = true,
    val isVibrationFeedback: Boolean = true
)

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

class PreferencesRepository(private val context: Context) : IPreferencesRepository {

    private object PreferencesKeys {
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val AUTO_REFRESH = booleanPreferencesKey("auto_refresh")
        val REFRESH_INTERVAL = intPreferencesKey("refresh_interval")
        val HIGH_PRECISION = booleanPreferencesKey("high_precision")
        val SHOW_UNITS = booleanPreferencesKey("show_units")
        val VIBRATION_FEEDBACK = booleanPreferencesKey("vibration_feedback")
    }

    private val _themeModeFlow = MutableStateFlow(ThemeMode.SYSTEM)
    override val themeModeFlow: StateFlow<ThemeMode> = _themeModeFlow.asStateFlow()

    override val preferencesFlow: Flow<AppPreferences> = context.dataStore.data.map { preferences ->
        val themeModeStr = preferences[PreferencesKeys.THEME_MODE] ?: ThemeMode.SYSTEM.name
        val themeMode = try {
            ThemeMode.valueOf(themeModeStr)
        } catch (e: IllegalArgumentException) {
            ThemeMode.SYSTEM
        }
        _themeModeFlow.value = themeMode
        AppPreferences(
            themeMode = themeMode,
            isAutoRefresh = preferences[PreferencesKeys.AUTO_REFRESH] ?: true,
            refreshInterval = preferences[PreferencesKeys.REFRESH_INTERVAL] ?: 1,
            isHighPrecision = preferences[PreferencesKeys.HIGH_PRECISION] ?: false,
            isShowUnits = preferences[PreferencesKeys.SHOW_UNITS] ?: true,
            isVibrationFeedback = preferences[PreferencesKeys.VIBRATION_FEEDBACK] ?: true
        )
    }

    override suspend fun updateThemeMode(mode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_MODE] = mode.name
            _themeModeFlow.value = mode
        }
    }

    override suspend fun updateAutoRefresh(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTO_REFRESH] = enabled
        }
    }

    override suspend fun updateRefreshInterval(interval: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.REFRESH_INTERVAL] = interval
        }
    }

    override suspend fun updateHighPrecision(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.HIGH_PRECISION] = enabled
        }
    }

    override suspend fun updateShowUnits(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SHOW_UNITS] = enabled
        }
    }

    override suspend fun updateVibrationFeedback(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.VIBRATION_FEEDBACK] = enabled
        }
    }

    override suspend fun resetPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}