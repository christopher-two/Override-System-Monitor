package org.override.system.monitor.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.override.system.monitor.core.preferences.PreferencesRepository
import org.override.system.monitor.feature.navigation.navigator.AppNavigator

class SettingsViewModel(
    private val appNavigator: AppNavigator,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.preferencesFlow.onEach { prefs ->
                _state.value = _state.value.copy(
                    isDarkMode = prefs.isDarkMode,
                    isAutoRefresh = prefs.isAutoRefresh,
                    refreshInterval = prefs.refreshInterval,
                    isHighPrecision = prefs.isHighPrecision,
                    isShowUnits = prefs.isShowUnits,
                    isVibrationFeedback = prefs.isVibrationFeedback,
                    isLoading = false
                )
            }.launchIn(viewModelScope)
        }
    }

    fun processAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.NavigateBack -> navigateBack()
            is SettingsAction.ResetDashboard -> showResetDialog()
            is SettingsAction.ShowResetDialog -> showResetDialog()
            is SettingsAction.HideResetDialog -> hideResetDialog()
            is SettingsAction.ToggleDarkMode -> toggleDarkMode(action.enabled)
            is SettingsAction.ToggleAutoRefresh -> toggleAutoRefresh(action.enabled)
            is SettingsAction.SetRefreshInterval -> setRefreshInterval(action.interval)
            is SettingsAction.ToggleHighPrecision -> toggleHighPrecision(action.enabled)
            is SettingsAction.ToggleShowUnits -> toggleShowUnits(action.enabled)
            is SettingsAction.ToggleVibrationFeedback -> toggleVibrationFeedback(action.enabled)
            is SettingsAction.OpenPrivacyPolicy -> { /* handled in composable */ }
            is SettingsAction.OpenTermsAndConditions -> { /* handled in composable */ }
        }
    }

    private fun navigateBack() {
        appNavigator.back()
    }

    private fun showResetDialog() {
        _state.value = _state.value.copy(showResetDialog = true)
    }

    private fun hideResetDialog() {
        _state.value = _state.value.copy(showResetDialog = false)
    }

    private fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateDarkMode(enabled)
        }
    }

    private fun toggleAutoRefresh(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateAutoRefresh(enabled)
        }
    }

    private fun setRefreshInterval(interval: Int) {
        viewModelScope.launch {
            preferencesRepository.updateRefreshInterval(interval)
        }
    }

    private fun toggleHighPrecision(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateHighPrecision(enabled)
        }
    }

    private fun toggleShowUnits(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateShowUnits(enabled)
        }
    }

    private fun toggleVibrationFeedback(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateVibrationFeedback(enabled)
        }
    }
}