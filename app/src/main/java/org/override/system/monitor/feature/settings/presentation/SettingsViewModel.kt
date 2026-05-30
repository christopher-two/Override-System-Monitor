package org.override.system.monitor.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.navigation.navigator.AppNavigator

class SettingsViewModel(
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    fun processAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.NavigateBack -> navigateBack()
            is SettingsAction.ResetDashboard -> resetDashboard()
        }
    }

    private fun navigateBack() {
        appNavigator.back()
    }

    private fun resetDashboard() {
    }
}