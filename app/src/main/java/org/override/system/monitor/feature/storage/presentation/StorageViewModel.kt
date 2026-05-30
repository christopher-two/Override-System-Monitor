package org.override.system.monitor.feature.storage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.override.system.monitor.feature.navigation.navigator.AppNavigator
import org.override.system.monitor.feature.storage.domain.usecase.GetStorageDataUseCase

class StorageViewModel(
    private val appNavigator: AppNavigator,
    private val getStorageDataUseCase: GetStorageDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(StorageState())
    val state: StateFlow<StorageState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getStorageDataUseCase().collect { data ->
                _state.update { it.copy(data = data, isLoading = false) }
            }
        }
    }

    fun processAction(action: StorageAction) {
        when (action) {
            is StorageAction.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        appNavigator.back()
    }
}