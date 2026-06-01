package org.override.system.monitor.feature.cpu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.cpu.domain.usecase.GetCpuDataUseCase
import org.override.system.monitor.feature.navigation.navigator.AppNavigator

class CpuViewModel(
    private val appNavigator: AppNavigator,
    private val getCpuDataUseCase: GetCpuDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CpuState())
    val state: StateFlow<CpuState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getCpuDataUseCase().collect { data ->
                _state.update { it.copy(data = data, isLoading = false) }
            }
        }
    }

    fun processAction(action: CpuAction) {
        when (action) {
            is CpuAction.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        appNavigator.back()
    }
}