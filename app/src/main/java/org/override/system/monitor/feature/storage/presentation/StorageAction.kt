package org.override.system.monitor.feature.storage.presentation

sealed class StorageAction {
    data object NavigateBack : StorageAction()
}