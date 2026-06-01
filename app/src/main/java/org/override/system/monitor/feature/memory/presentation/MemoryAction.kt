package org.override.system.monitor.feature.memory.presentation

sealed class MemoryAction {
    data object NavigateBack : MemoryAction()
}