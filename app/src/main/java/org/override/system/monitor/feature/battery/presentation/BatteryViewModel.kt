package org.override.system.monitor.feature.battery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.battery.domain.usecase.GetBatteryDataUseCase
import org.override.system.monitor.feature.navigation.navigator.AppNavigator

class BatteryViewModel(
    private val appNavigator: AppNavigator,
    private val getBatteryDataUseCase: GetBatteryDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BatteryState())
    val state: StateFlow<BatteryState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getBatteryDataUseCase().collect { data ->
                _state.update { it.copy(data = data, isLoading = false) }
            }
        }
    }

    fun processAction(action: BatteryAction) {
        when (action) {
            is BatteryAction.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        appNavigator.back()
    }
}