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
    val isDarkMode: Boolean = false,
    val isAutoRefresh: Boolean = true,
    val refreshInterval: Int = 1,
    val isHighPrecision: Boolean = false,
    val isShowUnits: Boolean = true,
    val isVibrationFeedback: Boolean = true
)

class PreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val AUTO_REFRESH = booleanPreferencesKey("auto_refresh")
        val REFRESH_INTERVAL = intPreferencesKey("refresh_interval")
        val HIGH_PRECISION = booleanPreferencesKey("high_precision")
        val SHOW_UNITS = booleanPreferencesKey("show_units")
        val VIBRATION_FEEDBACK = booleanPreferencesKey("vibration_feedback")
    }

    private val _darkModeFlow = MutableStateFlow(false)
    val darkModeFlow: StateFlow<Boolean> = _darkModeFlow.asStateFlow()

    val preferencesFlow: Flow<AppPreferences> = context.dataStore.data.map { preferences ->
        val darkMode = preferences[PreferencesKeys.DARK_MODE] ?: false
        _darkModeFlow.value = darkMode
        AppPreferences(
            isDarkMode = darkMode,
            isAutoRefresh = preferences[PreferencesKeys.AUTO_REFRESH] ?: true,
            refreshInterval = preferences[PreferencesKeys.REFRESH_INTERVAL] ?: 1,
            isHighPrecision = preferences[PreferencesKeys.HIGH_PRECISION] ?: false,
            isShowUnits = preferences[PreferencesKeys.SHOW_UNITS] ?: true,
            isVibrationFeedback = preferences[PreferencesKeys.VIBRATION_FEEDBACK] ?: true
        )
    }

    suspend fun updateDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_MODE] = enabled
            _darkModeFlow.value = enabled
        }
    }

    suspend fun updateAutoRefresh(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.AUTO_REFRESH] = enabled
        }
    }

    suspend fun updateRefreshInterval(interval: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.REFRESH_INTERVAL] = interval
        }
    }

    suspend fun updateHighPrecision(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.HIGH_PRECISION] = enabled
        }
    }

    suspend fun updateShowUnits(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SHOW_UNITS] = enabled
        }
    }

    suspend fun updateVibrationFeedback(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.VIBRATION_FEEDBACK] = enabled
        }
    }

    suspend fun resetPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}