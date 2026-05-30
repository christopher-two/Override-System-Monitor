package org.override.system.monitor.feature.storage.presentation

import org.override.system.monitor.feature.storage.domain.model.StorageData

data class StorageState(
    val data: StorageData? = null,
    val isLoading: Boolean = true
)

sealed class StorageAction {
    data object NavigateBack : StorageAction()
}