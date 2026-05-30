package org.override.system.monitor.feature.memory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.memory.domain.usecase.GetMemoryDataUseCase
import org.override.system.monitor.feature.navigation.navigator.AppNavigator

class MemoryViewModel(
    private val appNavigator: AppNavigator,
    private val getMemoryDataUseCase: GetMemoryDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MemoryState())
    val state: StateFlow<MemoryState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getMemoryDataUseCase().collect { data ->
                _state.update { it.copy(data = data, isLoading = false) }
            }
        }
    }

    fun processAction(action: MemoryAction) {
        when (action) {
            is MemoryAction.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        appNavigator.back()
    }
}